package com.integratedbookinfo.system.shared.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.Flow.Publisher;

import com.integratedbookinfo.system.shared.enums.BookSize;

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
	private BookSize size;
	private int listPrice;
	private String edition;
	private  LocalDate releaseDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String coverImagePath;
	private boolean deletedFlag;
}
