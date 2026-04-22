package com.integratedbookinfo.system.book.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSummaryDTO {

	private String bookId;
	private String isbn;
	private String publisherName;
	private String author;
	private String bookTitle;
	private LocalDate releaseDate;
}
