package com.integratedbookinfo.system.search;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.integratedbookinfo.system.search.dto.BookBasicInfoDTO;
import com.integratedbookinfo.system.search.dto.SearchSummaryListDTO;
import com.integratedbookinfo.system.search.dto.StoreBasicInfoDTO;
import com.integratedbookinfo.system.search.dto.StoreBookDTO;

@Mapper
public interface SearchMapper {

	public List<SearchSummaryListDTO> selectSearchSummayList(@Param("offset") int offset, @Param("limit")int limit, @Param("keyword") String keyword, @Param("genreId") String genreId);

	public BookBasicInfoDTO selectBookBasicInfo(String bookId);

	public List<StoreBookDTO> selectStoreBookInfo(String bookId);

	public StoreBasicInfoDTO selectStoreInfo(String storeId);


}
