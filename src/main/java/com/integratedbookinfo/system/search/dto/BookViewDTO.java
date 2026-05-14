package com.integratedbookinfo.system.search.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookViewDTO {

	private BookBasicInfoDTO bookBasicInfo;
	private List<SearchStoreBookDTO> storeBooks;
}
