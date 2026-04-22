package com.integratedbookinfo.system.search.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchSummaryListDTO {

	private String bookId;
	private String bookTitle;
	private String author;
	private String publisher;
	private List<SearchSummaryDTO> searchSummaries;
}
