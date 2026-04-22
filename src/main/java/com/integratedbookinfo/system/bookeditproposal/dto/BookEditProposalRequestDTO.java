package com.integratedbookinfo.system.bookeditproposal.dto;

import com.integratedbookinfo.system.shared.domain.BookDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookEditProposalRequestDTO {

	private BookDTO book; //変更後
	private String changeReason;
}
