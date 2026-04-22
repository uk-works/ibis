package com.integratedbookinfo.system.book.dto;

import com.integratedbookinfo.system.shared.domain.BookDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequestDTO {

	private BookDTO book;
	private StoreBookDTO storeBook;
}
