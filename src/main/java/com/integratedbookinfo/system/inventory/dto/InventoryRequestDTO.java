package com.integratedbookinfo.system.inventory.dto;

import com.integratedbookinfo.system.shared.enums.InventoryStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequestDTO {

	private String storeId;
	private String bookId;
	private int amount;
	private InventoryStatus inventoryStatus;
}
