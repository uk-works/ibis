package com.integratedbookinfo.system.dashboard.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDashboardResponseDTO {

	private List<StoreStatusDTO> storeStatuses;
	private List<BookEditProposalSummaryDTO> bookEditProposalSummaries;
}
