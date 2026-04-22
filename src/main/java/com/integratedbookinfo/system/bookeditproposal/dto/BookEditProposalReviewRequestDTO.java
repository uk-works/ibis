package com.integratedbookinfo.system.bookeditproposal.dto;

import com.integratedbookinfo.system.shared.enums.ProposalStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookEditProposalReviewRequestDTO {

	private int proposalId;
	private ProposalStatus proposalStatus;
	private String reason;
}