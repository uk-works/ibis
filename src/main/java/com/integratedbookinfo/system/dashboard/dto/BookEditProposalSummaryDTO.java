package com.integratedbookinfo.system.dashboard.dto;

import java.time.LocalDateTime;

import com.integratedbookinfo.system.shared.enums.ProposalStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookEditProposalSummaryDTO {

	private int proposalId;
	private ProposalStatus proposalStatus;
	private String bookTitle;
	private LocalDateTime proposedAt;
	private LocalDateTime updatedAt;
	private String storeName;
}
