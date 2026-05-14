package com.integratedbookinfo.system;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integratedbookinfo.system.book.BookMapper;
import com.integratedbookinfo.system.book.dto.BookSummaryDTO;
import com.integratedbookinfo.system.book.dto.StoreBookDTO;
import com.integratedbookinfo.system.dashboard.DashboardMapper;
import com.integratedbookinfo.system.dashboard.dto.BookEditProposalSummaryDTO;
import com.integratedbookinfo.system.dashboard.dto.StoreStatusDTO;
import com.integratedbookinfo.system.shared.domain.BookDTO;
import com.integratedbookinfo.system.shared.enums.AccountRole;
import com.integratedbookinfo.system.shared.enums.StoreStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DemoController {

	private final DashboardMapper dashboardMapper;
	private final BookMapper bookMapper;
	
	@GetMapping("/demo")
	public List<BookEditProposalSummaryDTO> demo(){
		return dashboardMapper.selectAdminBookEditProposalList();
	}
	
	@GetMapping("/demo2")
	public List<StoreStatusDTO> demo2(){
		return dashboardMapper.selectAdminStoreStatusList();
	}
	
	@GetMapping("/demo3")
	public List<BookEditProposalSummaryDTO> demo2(String storeId){
		return dashboardMapper.selectStoreBookEditProposalList("store0003");
	}
	
	@GetMapping("/demo4")
	public StoreStatus demo4(String storeId){
		return dashboardMapper.selectStoreStoreStatus("store0007");
	}
	
	@GetMapping("/demo5")
	public List<BookSummaryDTO> demo5(){
		return bookMapper.selectBookList(0, 5, AccountRole.STORE, "store0001", " ");
	}
	
	@GetMapping("/demo6")
	public BookDTO demo6() {
		return bookMapper.selectBookByBookId("book0001");
	}
	
	@GetMapping("/demo7")
	public StoreBookDTO demo7() {
		return bookMapper.selectStoreBookByStoreIdAndBookId("book0001","store0001");
	}
	
}
