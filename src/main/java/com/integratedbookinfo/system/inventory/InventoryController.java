package com.integratedbookinfo.system.inventory;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

	private final int NUM_PER_PAGE = 5;
	private final InventoryService inventoryService;
	
	@GetMapping("/list")
	public String showinventories(
			@RequestParam(name = "keyword", required = false) String keyword ,
			@RequestParam(name = "page", defaultValue = "1") Integer page, 
			HttpSession session,
			Model model) {
	
		String pageName = "";
		
		LoginUserRequestDTO loginUser = getLoginUser(session);

		model.addAttribute("displayName", loginUser.getDisplayName());
		model.addAttribute("accountRole", loginUser.getAccountRole());
		
		pageName = "inventory/admin/list";
		model.addAttribute("page", page);
		model.addAttribute("storeInventoryResponse", inventoryService.getStoreInventoriesByKeyword(page, NUM_PER_PAGE, keyword));
		model.addAttribute("totalPages", inventoryService.getStoreTotalPagesByKeyword(NUM_PER_PAGE, keyword));
		
		//System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(inventoryService.getStoreInventoriesByKeyword(NUM_PER_PAGE, NUM_PER_PAGE, keyword)));
		
		return pageName;
	}
	
	private LoginUserRequestDTO getLoginUser(HttpSession session) {
		LoginUserRequestDTO loginUser = new LoginUserRequestDTO();
		loginUser = (LoginUserRequestDTO) session.getAttribute("loginUser");

		return loginUser;
	}
}
