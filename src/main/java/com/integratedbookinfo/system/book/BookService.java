package com.integratedbookinfo.system.book;

import java.util.List;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;
import com.integratedbookinfo.system.book.dto.BookCreateRequestDTO;
import com.integratedbookinfo.system.book.dto.BookEditRequestDTO;
import com.integratedbookinfo.system.book.dto.BookListResponseDTO;
import com.integratedbookinfo.system.book.dto.StoreBookDTO;
import com.integratedbookinfo.system.shared.domain.BookDTO;
import com.integratedbookinfo.system.shared.domain.Genre;
import com.integratedbookinfo.system.shared.domain.Publisher;
import com.integratedbookinfo.system.shared.domain.StoreOptionDTO;
import com.integratedbookinfo.system.shared.enums.AccountRole;

@Service
public interface BookService {
	
	public BookListResponseDTO getBookList(
			int page, 
			int numPerPage, 
			AccountRole accountRole, 
			String storeId, 
			String keyword);

	public int getTotalPages(
			int numPerPage, 
			AccountRole accountRole, 
			String storeId, 
			String keyword);

	public List<Publisher> getPublishers();
	
	public List<Genre> getGenres();
	
	public List<StoreOptionDTO> getStoreOptions();	
	
	public List<StoreOptionDTO> getStoreOptionsByBookId(String bookId);
	
	public List<StoreBookDTO> getStoreBooksByBookId(String bookId);
	
	public BookEditRequestDTO getBookAndStoreBookForAdmin(AccountRole accountRole, String bookId);
	
	public BookEditRequestDTO getBookAndStoreBookForStore(AccountRole accountRole, String bookId, String storeId);
	
	public BookDTO getBookByIsbnOrJanCode(String isbn, String janCode);
	
	public void saveBook(BookCreateRequestDTO bookCreateRequestDTO, LoginUserRequestDTO loginUser);

	public boolean modifyBook(AccountRole accountRole, BookDTO book, StoreBookDTO storeBook);

	public void removeBook(String bookId);


}
