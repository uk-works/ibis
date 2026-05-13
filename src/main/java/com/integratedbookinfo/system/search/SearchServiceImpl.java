package com.integratedbookinfo.system.search;

import java.util.List;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.book.BookMapper;
import com.integratedbookinfo.system.search.dto.BookViewDTO;
import com.integratedbookinfo.system.search.dto.SearchSummaryListDTO;
import com.integratedbookinfo.system.search.dto.StoreBasicInfoDTO;
import com.integratedbookinfo.system.shared.domain.Genre;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchMapper searchMapper;
	private final BookMapper bookMapper;
	
	@Override
	public List<SearchSummaryListDTO> getSearchResult(int page, int numPerPage, String keyword, String genreId) {
		int offset = numPerPage * (page - 1);
		
		return searchMapper.selectSearchSummaryList(offset, numPerPage, keyword, genreId);
	}
	
	@Override
	public int getTotalPages(int numPerPage, String keyword, String genreId) {
		double totalNum = (double) searchMapper.countSearchSummary(keyword, genreId);
		System.out.println(totalNum);
		return (int) Math.ceil(totalNum / numPerPage);
	}
	
	@Override
	public List<Genre> getGenres(){
		return bookMapper.selectGenres();
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
