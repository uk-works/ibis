package com.integratedbookinfo.system.inventory;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;
import com.integratedbookinfo.system.inventory.dto.InventoryRequestDTO;
import com.integratedbookinfo.system.shared.enums.InventoryStatus;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;


@Controller
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

	private final int NUM_PER_PAGE = 5;
	private final InventoryService inventoryService;

	@GetMapping("/list")
	public String showinventories(
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			HttpSession session,
			Model model) {

		String pageName = "";

		LoginUserRequestDTO loginUser = getLoginUser(session);

		model.addAttribute("displayName", loginUser.getDisplayName());
		model.addAttribute("accountRole", loginUser.getAccountRole());

		pageName = "inventory/admin/list";
		model.addAttribute("page", page);
		model.addAttribute("storeInventoryResponse",
				inventoryService.getStoreInventoriesByKeyword(page, NUM_PER_PAGE, keyword));
		model.addAttribute("totalPages", inventoryService.getStoreTotalPagesByKeyword(NUM_PER_PAGE, keyword));

		//System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(inventoryService.getStoreInventoriesByKeyword(NUM_PER_PAGE, NUM_PER_PAGE, keyword)));

		return pageName;
	}

	@GetMapping("/create")
	public String createInventory(
			HttpSession session,
			Model model) {
		
		String pageName = "";
		LoginUserRequestDTO loginUser = getLoginUser(session);
		InventoryRequestDTO inventoryRequest = new InventoryRequestDTO();
		model.addAttribute("displayName", loginUser.getDisplayName());
		model.addAttribute("accountRole", loginUser.getAccountRole());

		model.addAttribute("storeOptions", inventoryService.getStoreOptions());
		model.addAttribute("bookOptions", inventoryService.getBookOptions());
		model.addAttribute("inventoryRequest", inventoryRequest);
		
		pageName = "inventory/admin/register";
		
		return pageName;
	}

	@PostMapping("/create")
	public String createInventory(
			@Validated @ModelAttribute("inventoryRequest") InventoryRequestDTO form,
			Errors errors,
			HttpSession session,
			Model model) {

		String pageName = "";
		LoginUserRequestDTO loginUser = getLoginUser(session);

		form.setInventoryStatus(InventoryStatus.ACTIVE);
		List<ObjectError> objList = errors.getAllErrors();
		for(ObjectError obj : objList) {
			System.out.println(obj.toString());
		}
		System.out.println(errors.hasErrors());
		System.out.println(errors.getErrorCount());
		if(errors.hasErrors()) {
			model.addAttribute("displayName", loginUser.getDisplayName());
			model.addAttribute("accountRole", loginUser.getAccountRole());

			model.addAttribute("storeOptions", inventoryService.getStoreOptions());
			model.addAttribute("bookOptions", inventoryService.getBookOptions());
			model.addAttribute("inventoryRequest", form);
			
			pageName = "inventory/admin/register";
			return pageName;
		}


		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(form));
		
		inventoryService.saveInventory(form);
		
		pageName = "redirect:/inventory/list";
		return pageName;
	}

	@PostMapping("/edit")
	public String editInventory(
			@Valid @ModelAttribute InventoryRequestDTO form,
			Errors errors,
			Model model
			) {
		
		inventoryService.modifyInventory(form);
		
		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(form));
		
		return "redirect:/inventory/list";
	}
	
	@PostMapping("/delete")
	public String deleteInventory(
			@RequestParam String storeId, 
			@RequestParam String bookId) {
		
		inventoryService.removeInventory(storeId, bookId);
		
		return "redirect:/inventory/list";
	}
	
	private LoginUserRequestDTO getLoginUser(HttpSession session) {
		LoginUserRequestDTO loginUser = new LoginUserRequestDTO();
		loginUser = (LoginUserRequestDTO) session.getAttribute("loginUser");

		return loginUser;
	}
}
