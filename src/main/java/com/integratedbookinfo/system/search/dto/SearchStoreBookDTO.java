package com.integratedbookinfo.system.search.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchStoreBookDTO {

	private String storeName;
	private String storeComment;
	private int storePrice;
	private String storeGenre;
	private String note;
}
