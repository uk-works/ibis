package com.integratedbookinfo.system.book;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

	private final BookMapper bookMapper;
	private final InventoryMapper inventoryMapper;
	private final BookEditProposalMapper bookEditProposalMapper;
	
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
	    	    
	    System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(storeOptions));
		System.out.println("=====");	    
	    
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
	public void saveBook(BookCreateRequestDTO bookCreateRequestDTO, LoginUserRequestDTO loginUser) {

		String recentBookId = bookMapper.selectLastBookIdForUpdate();

		String nextBookId = generateBookId(recentBookId);

		BookDTO book = bookCreateRequestDTO.getBook();
		book.setBookId(nextBookId);

		StoreBookDTO storeBook = bookCreateRequestDTO.getStoreBook();
		storeBook.setBookId(nextBookId);

		if (loginUser.getAccountRole() == AccountRole.STORE) {
			storeBook.setStoreId(loginUser.getStoreId());
		}

		// 画像登録処理
		saveImageFile(bookCreateRequestDTO.getBook());

		bookMapper.insertBook(book);

		bookMapper.insertStoreBook(storeBook);

	}

	@Override
	public void modifyBook(AccountRole accountRole, BookDTO book, StoreBookDTO storeBook) {
		
		int updateBookCount = 0;
		// 画像登録処理
		saveImageFile(book);
		
		if (accountRole.equals(AccountRole.ADMIN)) {
			updateBookCount = bookMapper.updateBook(book);
		}

		int updateStoreBookCount = bookMapper.updateStoreBook(book.getBookId(), storeBook);
		
		System.out.println("updateBook : " + updateBookCount);
		System.out.println("updateStoreBook : " + updateStoreBookCount);
		
	}

	@Override
	public void removeBook(String bookId) {
		
		bookMapper.deleteStoreBookByBookId(bookId);

		inventoryMapper.deleteInventoryByBookId(bookId);
		
		bookEditProposalMapper.deleteBookEditProposalByBookId(bookId);
		
		bookMapper.deleteBookByBookId(bookId);

	}

	public String generateBookId(String recentBookId) {
		String numberPart = recentBookId.substring(4);
		int next = Integer.parseInt(numberPart) + 1;

		if (next > 9999) {
			throw new RuntimeException("bookID上限に到達");
		}

		return String.format("book%04d", next);
	}
	
	public void saveImageFile(BookDTO book) {
		MultipartFile upFile = book.getCoverImageFile();
		
		final String UPLOAD_DIRECTORY = "C:/pleiades/workspace/images";
		
		if (upFile != null) {
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
