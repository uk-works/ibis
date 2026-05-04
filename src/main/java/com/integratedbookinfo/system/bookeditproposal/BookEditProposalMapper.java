package com.integratedbookinfo.system.bookeditproposal;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookEditProposalMapper {

	public int deleteBookEditProposalByBookId(String bookId);
}
