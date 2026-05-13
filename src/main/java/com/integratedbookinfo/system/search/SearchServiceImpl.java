package com.integratedbookinfo.system.search;

import java.util.List;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.search.dto.BookViewDTO;
import com.integratedbookinfo.system.search.dto.SearchSummaryListDTO;
import com.integratedbookinfo.system.search.dto.StoreBasicInfoDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchMapper searchMapper;
	
	@Override
	public List<SearchSummaryListDTO> getSearchResult(int page, int numPerPage, String keyword, String genreId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public int getTotalPages(int numPerPage, String keyword, String genreId) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	
	@Override
	public BookViewDTO getBookInfo(String bookId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public StoreBasicInfoDTO getStoreInfo(String storeId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
