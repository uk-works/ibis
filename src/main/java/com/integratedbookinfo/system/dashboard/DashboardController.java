package com.integratedbookinfo.system.dashboard;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;
import com.integratedbookinfo.system.shared.enums.AccountRole;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DashboardController {

	private final DashboardService dashboardService;
	
	@GetMapping("/dashboard")
	public String showDashboard(Model model, HttpSession session) {
		System.out.println("dashboard");
		
		LoginUserRequestDTO loginUser = (LoginUserRequestDTO) session.getAttribute("loginUser");
		
		String pageName = "";

		if (loginUser.getAccountRole().equals(AccountRole.ADMIN)) {
			pageName = "dashboard/admin";
		
		} else if (loginUser.getAccountRole().equals(AccountRole.STORE)){
			pageName =  "dashboard/store";
		
		} 
		
		model.addAttribute("displayName", loginUser.getDisplayName());
		model.addAttribute("accountRole", loginUser.getAccountRole());
		
		return pageName;
	}
}
