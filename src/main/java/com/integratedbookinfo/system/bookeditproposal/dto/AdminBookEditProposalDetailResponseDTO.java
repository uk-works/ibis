package com.integratedbookinfo.system.bookeditproposal.dto;

import com.integratedbookinfo.system.shared.domain.BookDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminBookEditProposalDetailResponseDTO {

	private BookDTO beforeBook; //書籍マスタより
	private BookDTO afterBook; //書籍編集提案より
	private String comment;
}
