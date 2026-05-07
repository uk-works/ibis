package com.integratedbookinfo.system.inventory.dto;

import jakarta.validation.constraints.Min;

import com.integratedbookinfo.system.shared.enums.InventoryStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequestDTO {

	private String storeId;
	private String bookId;
	
	@Min(value = 1, message = "在庫数は1以上を入力してください")
	private int amount;
	private InventoryStatus inventoryStatus;
}
