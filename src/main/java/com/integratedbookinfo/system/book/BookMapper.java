package com.integratedbookinfo.system.book;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.integratedbookinfo.system.book.dto.BookSummaryDTO;
import com.integratedbookinfo.system.book.dto.StoreBookDTO;
import com.integratedbookinfo.system.shared.domain.BookDTO;
import com.integratedbookinfo.system.shared.enums.AccountRole;

/**
 * 
 */
@Mapper
public interface BookMapper {

	public List<BookSummaryDTO> selectBookList(
			@Param("offset") int offset, 
			@Param("limit")int limit, 
			@Param("accountRole") AccountRole accountRole, 
			@Param("storeId") String storeId,
			@Param("keyword") String keyword);

	public Long countBookRecord(
			@Param("accountRole") AccountRole accountRole, 
			@Param("storeId") String storeId,
			@Param("keyword") String keyword);

	public String selectLastBookIdForUpdate();
	
	public int insertBook(BookDTO bookDto);

	public int insertStoreBook(StoreBookDTO storeBookDto);

	public int updateBook(BookDTO bookDto);

	public int updateStoreBook(StoreBookDTO storeBookDto);

	public int deleteBook(String BookId);


}
