package com.integratedbookinfo.system.book;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.book.dto.BookCreateRequestDTO;
import com.integratedbookinfo.system.book.dto.BookListResponseDTO;
import com.integratedbookinfo.system.book.dto.StoreBookDTO;
import com.integratedbookinfo.system.shared.domain.BookDTO;
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

	public void saveBook(BookCreateRequestDTO bookCreateRequestDTO);

	public void modifyBook(BookDTO bookDto);

	public void modifyStoreBook(StoreBookDTO storeBookDto);

	public void removeBook(String bookId);


}
