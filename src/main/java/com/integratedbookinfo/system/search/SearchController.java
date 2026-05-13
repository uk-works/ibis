package com.integratedbookinfo.system.search;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

	private final int NUM_PER_PAGE = 10;
	private final SearchService searchService;

	public String search(Model model) {
		return "search/book-search";
	}

	@GetMapping("/result")
	public String showSearchResult(
			@RequestParam(name = "keyword", required = true) String keyword,
			@RequestParam(name = "page", defaultValue = "1") Integer page, 
			@RequestParam(name = "genre", defaultValue = "0") String genre,
			Model model){
		
		return "search/book-search-result";
	}

	@GetMapping("/book")
	public String showBookInfo(Model model) {
		return "search/book-detail";
	}

	@GetMapping("/store")
	public String showStoreInfo(Model model) {
		return "search/store-detail";
	}
}
