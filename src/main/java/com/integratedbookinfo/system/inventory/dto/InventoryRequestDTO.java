package com.integratedbookinfo.system.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.integratedbookinfo.system.shared.enums.InventoryStatus;
import com.integratedbookinfo.system.validationgroup.InventoryEditGroup;
import com.integratedbookinfo.system.validationgroup.InventoryRegisterGroup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequestDTO {

	private String storeId;
	
	@NotBlank(groups = {InventoryRegisterGroup.class, InventoryEditGroup.class})
	private String bookId;
	
	@NotNull(groups = {InventoryRegisterGroup.class, InventoryEditGroup.class})
	@Min(value = 1, message = "在庫数は1以上を入力してください", 
			groups = {InventoryRegisterGroup.class})
	private Integer amount;
	
	@NotNull(groups = {InventoryEditGroup.class})
	private InventoryStatus inventoryStatus;
}
