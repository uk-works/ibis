package com.integratedbookinfo.system.search.dto;

import java.util.List;

import com.integratedbookinfo.system.shared.domain.Publisher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchSummaryListDTO {

	private String bookId;
	private String bookTitle;
	private String author;
	private Publisher publisher;
	private List<SearchSummaryDTO> summaries;
}
