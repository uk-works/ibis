package com.integratedbookinfo.system.bookeditproposal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bookeditproposal")
public class BookEditProposalController {
	
	private final int NUM_PER_PAGE = 10;
	private final BookEditProposalService BookEditProposalService;
}
