package com.integratedbookinfo.system.dashboard;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.dashboard.dto.AdminDashboardResponseDTO;
import com.integratedbookinfo.system.dashboard.dto.StoreDashboardResponseDTO;

import lombok.RequiredArgsConstructor;

/**
 * DashboardServiceの実装クラス
 *
 * DBから取得した情報をもとに、
 * ダッシュボード表示用DTOを生成して返却する
 *
 * 管理者用・書店員用で異なる取得ロジックを持ち、
 * Mapperを通じて必要なデータを集約し画面用構造に変換する
 */
@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

	private final DashboardMapper dashboardMapper;
	
	@Override
	public AdminDashboardResponseDTO getAdminDashboard() {
		
		AdminDashboardResponseDTO adminDashboardResponse = new AdminDashboardResponseDTO();
		
		adminDashboardResponse.setBookEditProposalSummaries(dashboardMapper.selectAdminBookEditProposalList());
		adminDashboardResponse.setStoreStatuses(dashboardMapper.selectAdminStoreStatusList());

		return adminDashboardResponse;
	}
	
	@Override
	public StoreDashboardResponseDTO getStoreDashboard(String storeId) {
		
		StoreDashboardResponseDTO storeDashboardResponse = new StoreDashboardResponseDTO();
		
		storeDashboardResponse.setBookEditProposalSummaries(dashboardMapper.selectStoreBookEditProposalList(storeId));
		storeDashboardResponse.setStoreStatus(dashboardMapper.selectStoreStoreStatus(storeId));
		
		return storeDashboardResponse;
	}
}
