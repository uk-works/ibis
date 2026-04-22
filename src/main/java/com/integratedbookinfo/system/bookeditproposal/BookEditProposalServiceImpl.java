package com.integratedbookinfo.system.bookeditproposal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookEditProposalServiceImpl implements BookEditProposalService {

	private final BookEditProposalMapper BookEditProposalMapper;
}
