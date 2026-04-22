package com.integratedbookinfo.system.store.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreListResponseDTO {

	private List<StoreSummaryDTO> storeSummaries;
	
}
