package com.integratedbookinfo.system.book;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.book.dto.BookCreateRequestDTO;
import com.integratedbookinfo.system.book.dto.BookListResponseDTO;
import com.integratedbookinfo.system.book.dto.StoreBookDTO;
import com.integratedbookinfo.system.shared.domain.BookDTO;
import com.integratedbookinfo.system.shared.enums.AccountRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookMapper bookMapper;
	
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
	public void saveBook(BookCreateRequestDTO bookCreateRequestDTO) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	@Override
	public void modifyBook(BookDTO bookDto) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	@Override
	public void modifyStoreBook(StoreBookDTO storeBookDto) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	@Override
	public void removeBook(String bookId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
}
