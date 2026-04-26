package com.integratedbookinfo.system.dashboard;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;
import com.integratedbookinfo.system.shared.enums.AccountRole;

import lombok.RequiredArgsConstructor;

/**
 * ダッシュボードのリクエストを受け付けるコントローラー
 * ダッシュボードの表示を担当する
 */
@Controller
@RequiredArgsConstructor
public class DashboardController {

	private final DashboardService dashboardService;
	
	/**
	 * ダッシュボードを表示する
	 * ロール毎に表示内容が異なるため、ロールごとにService以下から情報を得る
	 * @param model ログイン画面を表示する
	 * @param session セッション情報
	 * @return ダッシュボード画面（管理者 or 書店員）
	 */
	@GetMapping("/dashboard")
	public String showDashboard(Model model, HttpSession session) {
		System.out.println("dashboard");
		
		LoginUserRequestDTO loginUser = (LoginUserRequestDTO) session.getAttribute("loginUser");
		
		String pageName = "";

		if (loginUser.getAccountRole().equals(AccountRole.ADMIN)) {
			model.addAttribute("dashboardInfo", dashboardService.getAdminDashboard());
			pageName = "dashboard/admin";
		
		} else if (loginUser.getAccountRole().equals(AccountRole.STORE)){
			model.addAttribute("dashboardInfo", dashboardService.getStoreDashboard(loginUser.getStoreId()));
			pageName =  "dashboard/store";
		
		} 
		
		model.addAttribute("displayName", loginUser.getDisplayName());
		model.addAttribute("accountRole", loginUser.getAccountRole());
		
		return pageName;
	}
}
