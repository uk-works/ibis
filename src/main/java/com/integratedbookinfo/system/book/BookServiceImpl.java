package com.integratedbookinfo.system.book;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;
import com.integratedbookinfo.system.book.dto.BookCreateRequestDTO;
import com.integratedbookinfo.system.book.dto.BookEditRequestDTO;
import com.integratedbookinfo.system.book.dto.BookListResponseDTO;
import com.integratedbookinfo.system.book.dto.StoreBookDTO;
import com.integratedbookinfo.system.bookeditproposal.BookEditProposalMapper;
import com.integratedbookinfo.system.inventory.InventoryMapper;
import com.integratedbookinfo.system.shared.domain.BookDTO;
import com.integratedbookinfo.system.shared.domain.Genre;
import com.integratedbookinfo.system.shared.domain.Publisher;
import com.integratedbookinfo.system.shared.domain.StoreOptionDTO;
import com.integratedbookinfo.system.shared.enums.AccountRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

	private final BookMapper bookMapper;
	private final InventoryMapper inventoryMapper;
	private final BookEditProposalMapper bookEditProposalMapper;
	
	@Value("${upload.image.path:}")
	private String UPLOAD_DIRECTORY;
	
	@Override
	public BookListResponseDTO getBookList(int page, int numPerPage, AccountRole accountRole, String storeId,
			String keyword) {
		int offset = numPerPage * (page - 1);

		BookListResponseDTO bookListResponse = new BookListResponseDTO();

		bookListResponse.setBooks(bookMapper.selectBookList(offset, numPerPage, accountRole, storeId, keyword));

		return bookListResponse;
	}

	@Override
	public int getTotalPages(int numPerPage, AccountRole accountRole, String storeId, String keyword) {
		double totalNum = (double) bookMapper.countBookRecord(accountRole, storeId, keyword);

		return (int) Math.ceil(totalNum / numPerPage);
	}

	@Override
	public List<Publisher> getPublishers() {
		return bookMapper.selectPublishers();
	}

	@Override
	public List<Genre> getGenres() {
		return bookMapper.selectGenres();
	}

	@Override
	public List<StoreOptionDTO> getStoreOptions() {
		return bookMapper.selectStoreOptions();
	}
	
	@Override
	public List<StoreOptionDTO> getStoreOptionsByBookId(String bookId) {
		
	    List<StoreBookDTO> storeBooks = bookMapper.selectStoreBooksByBookId(bookId);
	    
	    List<StoreOptionDTO> storeOptions = bookMapper.selectStoreOptions();
	    
	    Set<String> hasBookStoreIds = storeBooks.stream()
	        .map(StoreBookDTO::getStoreId)
	        .collect(Collectors.toSet());

	    storeOptions.forEach(store -> {
	    	
	    	if(!hasBookStoreIds.contains(store.getStoreId())) {
	    		store.setStoreName(store.getStoreName() + "（未登録）");
	    	}
	    	
	    });

	    return storeOptions;
	}
	
	@Override
	public List<StoreBookDTO> getStoreBooksByBookId(String bookId) {
		
		return bookMapper.selectStoreBooksByBookId(bookId);
	}
	
	@Override
	public BookEditRequestDTO getBookAndStoreBookForAdmin(AccountRole accountRole, String bookId) {

		BookDTO book = bookMapper.selectBookByBookId(bookId);
		
		BookEditRequestDTO bookEditRequest = new BookEditRequestDTO();

		bookEditRequest.setBook(book);
		bookEditRequest.setStoreBook(new StoreBookDTO());
		
		return bookEditRequest;
	}

	@Override
	public BookEditRequestDTO getBookAndStoreBookForStore(AccountRole accountRole, String bookId, String storeId) {

		BookDTO book = bookMapper.selectBookByBookId(bookId);

		StoreBookDTO storeBook = bookMapper.selectStoreBookByStoreIdAndBookId(bookId, storeId);

		BookEditRequestDTO bookEditRequest = new BookEditRequestDTO();

		bookEditRequest.setBook(book);
		bookEditRequest.setStoreBook(storeBook);

		return bookEditRequest;

	}

	@Override
	public BookDTO getBookByIsbnOrJanCode(String isbn, String janCode) {
		
		return bookMapper.selectFindBookByIsbnOrJan(isbn, janCode);
	}	
	
	@Override
	public void saveBook(BookCreateRequestDTO bookCreateRequestDTO, LoginUserRequestDTO loginUser) {

		BookDTO originBook = bookMapper.selectFindBookByIsbnOrJan(
				bookCreateRequestDTO.getBook().getIsbn(), bookCreateRequestDTO.getBook().getJanCode());
		String recentBookId = "";
		String nextBookId = "";

		StoreBookDTO storeBook = bookCreateRequestDTO.getStoreBook();
		
		if (originBook == null) {
			recentBookId = bookMapper.selectLastBookIdForUpdate();

			nextBookId = generateBookId(recentBookId);

			BookDTO book = bookCreateRequestDTO.getBook();
			book.setBookId(nextBookId);
			
			// 画像登録処理
			saveImageFile(bookCreateRequestDTO.getBook());

			bookMapper.insertBook(book);
			
			storeBook.setBookId(nextBookId);
		} else {
			storeBook.setBookId(originBook.getBookId());			
		}


		if (loginUser.getAccountRole() == AccountRole.STORE) {
			storeBook.setStoreId(loginUser.getStoreId());
		}

		bookMapper.insertStoreBook(storeBook);

	}

	@Override
	public boolean modifyBook(AccountRole accountRole, BookDTO book, StoreBookDTO storeBook) {

		int updateStoreBookCount = 0;
		
		if (accountRole.equals(AccountRole.ADMIN)) {

			saveImageFile(book);
			
			bookMapper.updateBook(book);
		}

		// isRegisterIntent:true 書店個別項目が新規登録(未登録), false 既存項目の編集
		if (!storeBook.isRegisterIntent()) {

			updateStoreBookCount = bookMapper.updateStoreBook(book.getBookId(), storeBook);
			
			if (updateStoreBookCount == 0) {
				return false;
			}
			
		} else {
			// 書籍編集（書店書籍 新規追加の場合）
			// 書籍追加時に書店書籍の登録が無い書店については、書店書籍レコードが存在しないためINSERTを行う
			int existStoreBook = bookMapper.countStoreBookByStoreIdAndBookId(book.getBookId(), storeBook.getStoreId());
			
			if (existStoreBook == 0) {
				storeBook.setBookId(book.getBookId());
				bookMapper.insertStoreBook(storeBook);
				
			} else {
				return false;
			}
			
		}

		return true;
	}

	@Override
	public void removeBook(String bookId) {
		
		bookMapper.deleteStoreBookByBookId(bookId);

		inventoryMapper.deleteInventoryByBookId(bookId);
		
		bookEditProposalMapper.deleteBookEditProposalByBookId(bookId);
		
		bookMapper.deleteBookByBookId(bookId);

	}

	public String generateBookId(String recentBookId) {
		
		if (recentBookId == null) {
			return "book0001";
		}
		
		String numberPart = recentBookId.substring(4);
		int next = Integer.parseInt(numberPart) + 1;

		if (next > 9999) {
			throw new RuntimeException("bookID上限に到達");
		}

		return String.format("book%04d", next);
	}
	
	public void saveImageFile(BookDTO book) {
		MultipartFile upFile = book.getCoverImageFile();
		
		if (upFile != null && !upFile.isEmpty()) {
			String fileName = upFile.getOriginalFilename();

			book.setCoverImagePath(fileName);

			File dest = new File(UPLOAD_DIRECTORY + "/" + fileName);
			System.out.println(dest);
			try {
				upFile.transferTo(dest);
			} catch (IOException e) {
				System.out.println(e);
				System.out.println("画像ファイルを保存できませんでした。");

			}
		}
	}
}
