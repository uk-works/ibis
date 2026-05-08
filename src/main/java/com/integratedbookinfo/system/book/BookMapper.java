package com.integratedbookinfo.system.book;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.integratedbookinfo.system.book.dto.BookSummaryDTO;
import com.integratedbookinfo.system.book.dto.StoreBookDTO;
import com.integratedbookinfo.system.shared.domain.BookDTO;
import com.integratedbookinfo.system.shared.domain.BookOptionDTO;
import com.integratedbookinfo.system.shared.domain.Genre;
import com.integratedbookinfo.system.shared.domain.Publisher;
import com.integratedbookinfo.system.shared.domain.StoreOptionDTO;
import com.integratedbookinfo.system.shared.enums.AccountRole;

/**
 * 
 */
@Mapper
public interface BookMapper {

	public List<BookSummaryDTO> selectBookList(
			@Param("offset") int offset, 
			@Param("limit") int limit, 
			@Param("accountRole") AccountRole accountRole, 
			@Param("storeId") String storeId,
			@Param("keyword") String keyword);

	public Long countBookRecord(
			@Param("accountRole") AccountRole accountRole, 
			@Param("storeId") String storeId,
			@Param("keyword") String keyword);

	public String selectLastBookIdForUpdate();
	
	public List<Publisher> selectPublishers();
	
	public List<Genre> selectGenres();
	
	public List<StoreOptionDTO> selectStoreOptions();
	
	public BookDTO selectBookByBookId(String bookId);
	
	public StoreBookDTO selectStoreBookByStoreIdAndBookId(String bookId, String storeId);
	
	public List<StoreBookDTO> selectStoreBooksByBookId(String bookId);
	
	public List<BookOptionDTO> selectBookOptions();
	
	public int insertBook(BookDTO bookDto);

	public int insertStoreBook(StoreBookDTO storeBookDto);

	public int updateBook(BookDTO bookDto);

	public int updateStoreBook(@Param("bookId") String bookId, @Param("storeBook") StoreBookDTO storeBook);

	/* 該当書籍に関するデータが保持されているものはすべて論理削除
	 * 書籍マスタ・書店書籍・在庫・書籍編集提案 削除 */
	public int deleteBookByBookId(String bookId);
	
	public int deleteStoreBookByBookIdAndStoreId(@Param("bookId") String bookId, @Param("storeId") String storeId);
	
	public int deleteStoreBookByBookId(String bookId);

}
