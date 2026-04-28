package com.integratedbookinfo.system.shared.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.integratedbookinfo.system.shared.enums.BookSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

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
	private  LocalDate releaseDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String coverImagePath;
	private boolean deletedFlag;
}
