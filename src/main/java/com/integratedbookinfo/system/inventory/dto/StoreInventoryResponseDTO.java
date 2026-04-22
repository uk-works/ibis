package com.integratedbookinfo.system.inventory.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreInventoryResponseDTO {

	private List<InventorySummaryDTO> inventorySummaries;
}
