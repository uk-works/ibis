package com.integratedbookinfo.system.search;

import java.util.List;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.book.BookMapper;
import com.integratedbookinfo.system.search.dto.BookBasicInfoDTO;
import com.integratedbookinfo.system.search.dto.BookViewDTO;
import com.integratedbookinfo.system.search.dto.SearchSummaryListDTO;
import com.integratedbookinfo.system.search.dto.StoreBasicInfoDTO;
import com.integratedbookinfo.system.shared.domain.Genre;
import com.integratedbookinfo.system.shared.util.CCodeUtil;
import com.integratedbookinfo.system.store.StoreMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchMapper searchMapper;
	private final BookMapper bookMapper;
	private final StoreMapper storeMapper;
	
	@Override
	public List<SearchSummaryListDTO> getSearchResult(int page, int numPerPage, String keyword, String genreId) {
		int offset = numPerPage * (page - 1);
		
		return searchMapper.selectSearchSummaryList(offset, numPerPage, keyword, genreId);
	}
	
	@Override
	public int getTotalPages(int numPerPage, String keyword, String genreId) {
		double totalNum = (double) searchMapper.countSearchSummary(keyword, genreId);

		return (int) Math.ceil(totalNum / numPerPage);
	}
	
	@Override
	public List<Genre> getGenres(){
		return bookMapper.selectGenres();
	}
	
	@Override
	public BookViewDTO getBookInfo(String bookId) {
		BookViewDTO bookView = new BookViewDTO();
		
		BookBasicInfoDTO bookBasicInfo = bookMapper.selectBookBasicInfoByBookId(bookId);

		bookBasicInfo.setCCodeName(CCodeUtil.convertCCodeToName(bookBasicInfo.getCCode()));

		bookView.setBookBasicInfo(bookBasicInfo);
		bookView.setStoreBooks(bookMapper.selectSearchStoreBookByBookId(bookId));
		
		return bookView;
	}
	
	@Override
	public StoreBasicInfoDTO getStoreInfo(String storeId) {
		
		return storeMapper.selectStoreDetailByStoreId(storeId);
	}

}
