package com.integratedbookinfo.system.search.dto;

import java.time.LocalDate;

import com.integratedbookinfo.system.shared.domain.Genre;
import com.integratedbookinfo.system.shared.domain.Publisher;
import com.integratedbookinfo.system.shared.enums.BookSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookBasicInfoDTO {

	private String bookId;
	private String isbn;
	private String cCode;
	private String cCodeName;
	private String janCode;
	private Genre genre;
	private Publisher publisher;
	private String author;
	private String bookTitle;
	private BookSize bookSize;
	private int listPrice;
	private String edition;
	private LocalDate releaseDate;
	private String coverImagePath;
}
