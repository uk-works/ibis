package com.integratedbookinfo.system;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integratedbookinfo.system.dashboard.DashboardMapper;
import com.integratedbookinfo.system.dashboard.dto.BookEditProposalSummaryDTO;
import com.integratedbookinfo.system.dashboard.dto.StoreStatusDTO;
import com.integratedbookinfo.system.shared.enums.StoreStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DemoController {

	private final DashboardMapper dashboardMapper;
	
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
}
