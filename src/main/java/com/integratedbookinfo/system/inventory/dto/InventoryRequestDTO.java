package com.integratedbookinfo.system.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import com.integratedbookinfo.system.shared.enums.InventoryStatus;
import com.integratedbookinfo.system.validationgroup.InventoryEditGroup;
import com.integratedbookinfo.system.validationgroup.InventoryRegisterGroup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequestDTO {

	//@NotBlank(groups = {InventoryRegisterGroup.class, InventoryEditGroup.class})
	private String storeId;
	
	@NotBlank(groups = {InventoryRegisterGroup.class, InventoryEditGroup.class})
	private String bookId;
	
	@Min(value = 1, message = "在庫数は1以上を入力してください", 
			groups = {InventoryRegisterGroup.class, InventoryEditGroup.class})
	private int amount;
	
	@NotBlank(groups = {InventoryEditGroup.class})
	private InventoryStatus inventoryStatus;
}
