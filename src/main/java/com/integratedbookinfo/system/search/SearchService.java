package com.integratedbookinfo.system.search;

import java.util.List;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.search.dto.BookViewDTO;
import com.integratedbookinfo.system.search.dto.SearchSummaryListDTO;
import com.integratedbookinfo.system.search.dto.StoreBasicInfoDTO;
import com.integratedbookinfo.system.shared.domain.Genre;

@Service
public interface SearchService {

	public List<SearchSummaryListDTO> getSearchResult(int page, int numPerPage, String keyword, String genreId);

	public int getTotalPages(int numPerPage, String keyword, String genreId);
	
	public List<Genre> getGenres();
	
	public BookViewDTO getBookInfo(String bookId);

	public StoreBasicInfoDTO getStoreInfo(String storeId);



}
