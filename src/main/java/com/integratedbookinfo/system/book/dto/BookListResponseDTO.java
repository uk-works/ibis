package com.integratedbookinfo.system.book.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookListResponseDTO {

	private List<BookSummaryDTO> books;
}
