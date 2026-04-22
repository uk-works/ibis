package com.integratedbookinfo.system.inventory.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminInventoryResponseDTO {

	private List<StoreInventoryDTO> storeInventories;
}
