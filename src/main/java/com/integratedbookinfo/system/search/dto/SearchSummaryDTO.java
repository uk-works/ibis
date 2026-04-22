package com.integratedbookinfo.system.search.dto;

import com.integratedbookinfo.system.shared.enums.InventoryStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchSummaryDTO {

	private String shopId;
	private String shopName;
	private int amount;
	private InventoryStatus inventoryStatus;
}
