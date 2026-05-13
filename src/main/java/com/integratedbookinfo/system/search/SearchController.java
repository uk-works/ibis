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

	private final int NUM_PER_PAGE = 5;
	private final SearchService searchService;

	@GetMapping("")
	public String search(Model model) {
		
		model.addAttribute("genres", searchService.getGenres());
		
		return "search/book-search";
	}

	@GetMapping("/result")
	public String showSearchResult(
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "genre", required = false) String genre,
			@RequestParam(name = "page", defaultValue = "1") Integer page, 
			Model model){
		
		model.addAttribute("searchSummaries", searchService.getSearchResult(page, NUM_PER_PAGE, keyword, genre));
		model.addAttribute("totalPages", searchService.getTotalPages(NUM_PER_PAGE, keyword, genre));
		model.addAttribute("page", page);
		
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
