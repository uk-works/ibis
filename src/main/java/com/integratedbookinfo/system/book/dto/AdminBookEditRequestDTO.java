package com.integratedbookinfo.system.book.dto;

import jakarta.validation.Valid;

import com.integratedbookinfo.system.shared.domain.BookDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminBookEditRequestDTO {

	@Valid
	private BookDTO book;
	
	@Valid
	private StoreBookDTO storeBook;
}
