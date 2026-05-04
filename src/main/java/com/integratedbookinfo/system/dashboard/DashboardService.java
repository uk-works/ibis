package com.integratedbookinfo.system.dashboard;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.dashboard.dto.AdminDashboardResponseDTO;
import com.integratedbookinfo.system.dashboard.dto.StoreDashboardResponseDTO;

/**
 * ダッシュボード画面に必要な情報を提供するサービスインターフェース
 *
 * ユーザーの権限（管理者 / 書店員）に応じて、
 * 表示用データ（DTO）を生成する処理を定義する
 */
@Service
public interface DashboardService {

	public AdminDashboardResponseDTO getAdminDashboard();
	
	public StoreDashboardResponseDTO getStoreDashboard(String storeId);
	
}
