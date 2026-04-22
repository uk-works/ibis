package com.integratedbookinfo.system.dashboard.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDashboardResponseDTO {

	private StoreStatusDTO storeStatus;
	private List<BookEditProposalSummaryDTO> bookEditProposalSummaries;
}
