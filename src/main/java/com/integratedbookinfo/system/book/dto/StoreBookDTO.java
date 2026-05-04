package com.integratedbookinfo.system.book.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreBookDTO {

	private String bookId;
	private String storeId;
	
	private String storeComment;

	private String storeGenre;
	
	private String storePrice;
	
	private String note;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private boolean deletedFlag;
}
