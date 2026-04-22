package com.integratedbookinfo.system.book.dto;

import java.util.List;

import com.integratedbookinfo.system.shared.domain.BookDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminBookEditRequestDTO {

	private BookDTO book;
	private List<StoreBookDTO> storeBooks;
}
