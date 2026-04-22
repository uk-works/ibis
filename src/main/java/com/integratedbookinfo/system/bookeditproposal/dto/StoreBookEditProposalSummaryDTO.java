package com.integratedbookinfo.system.bookeditproposal.dto;

import java.time.LocalDateTime;

import com.integratedbookinfo.system.shared.enums.ProposalStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreBookEditProposalSummaryDTO {

	private int proposalId;
	private ProposalStatus proposalStatus;
	private String bookTitle;
	private LocalDateTime proposedAt;
}