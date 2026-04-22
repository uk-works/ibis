package com.integratedbookinfo.system.inventory.dto;

import java.time.LocalDateTime;

import com.integratedbookinfo.system.shared.enums.InventoryStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventorySummaryDTO {

	private String bookId;
	private String storeId;
	private String bookTitle;
	private int amount;
	private InventoryStatus inventoryStatus;
	private LocalDateTime updatedAt;
}
