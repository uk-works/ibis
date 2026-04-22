package com.integratedbookinfo.system.inventory.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreInventoryDTO {

	private String storeId;
	private String storeName;
	private List<InventorySummaryDTO> inventorySummaries;
}
