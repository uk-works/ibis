package com.integratedbookinfo.system.inventory;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.integratedbookinfo.system.auth.AuthController;
import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;
import com.integratedbookinfo.system.inventory.dto.InventoryRequestDTO;
import com.integratedbookinfo.system.shared.enums.AccountRole;
import com.integratedbookinfo.system.shared.enums.InventoryStatus;
import com.integratedbookinfo.system.validationgroup.InventoryEditGroup;
import com.integratedbookinfo.system.validationgroup.InventoryRegisterGroup;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final AuthController authController;

	private final int NUM_PER_PAGE = 2;
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
		model.addAttribute("keyword", keyword);
		
		if (loginUser.getAccountRole().equals(AccountRole.ADMIN)) {
			model.addAttribute("page", page);
			model.addAttribute("storeInventoryResponse", inventoryService.getStoreInventoriesByKeyword(page, NUM_PER_PAGE, keyword));
			model.addAttribute("totalPages", inventoryService.getStoreTotalPagesByKeyword(NUM_PER_PAGE, keyword));
			pageName = "inventory/admin/list";
		} else {
			model.addAttribute("page", page);
			model.addAttribute("storeInventoryResponse", inventoryService.getInventorySummariesByKeyword(page, NUM_PER_PAGE, loginUser.getStoreId(), keyword));
			model.addAttribute("totalPages", inventoryService.getInventoryTotalPagesByKeyword(NUM_PER_PAGE, loginUser.getStoreId(), keyword));
			pageName = "inventory/store/list";
		}

		return pageName;
	}

	@GetMapping("/create")
	public String createInventory(
			HttpSession session,
			Model model) {
		
		String pageName = "";
		LoginUserRequestDTO loginUser = getLoginUser(session);
		InventoryRequestDTO inventoryRequest = new InventoryRequestDTO();
		inventoryRequest.setAmount(1);
		
		model.addAttribute("displayName", loginUser.getDisplayName());
		model.addAttribute("accountRole", loginUser.getAccountRole());

		model.addAttribute("inventoryRequest", inventoryRequest);

		if (loginUser.getAccountRole().equals(AccountRole.ADMIN)){
			model.addAttribute("storeOptions", inventoryService.getStoreOptions());
			model.addAttribute("bookOptions", inventoryService.getBookOptions());
			

			pageName = "inventory/admin/register";
		} else {
			model.addAttribute("bookOptions", inventoryService.getBookOptionsByStoreId(loginUser.getStoreId()));
		
			pageName = "inventory/store/register";	
		}
		
		return pageName;
	}

	@PostMapping("/create")
	public String createInventory(
			@Validated(InventoryRegisterGroup.class) @ModelAttribute("inventoryRequest") InventoryRequestDTO form,
			Errors errors,
			HttpSession session,
			Model model) {

		LoginUserRequestDTO loginUser = getLoginUser(session);
		
		boolean isStoreIdError = (loginUser.getAccountRole().equals(AccountRole.ADMIN) 
				&& (form.getStoreId() == null || form.getStoreId().isBlank()));
		
		boolean isBookIdError = form.getBookId() == null || form.getBookId().isBlank();
		
		if(errors.hasErrors() || isStoreIdError || isBookIdError) {
			
			if (isStoreIdError || isBookIdError) {
				errors.reject("error.inventory.no-available-stock");
			}
			
			model.addAttribute("displayName", loginUser.getDisplayName());
			model.addAttribute("accountRole", loginUser.getAccountRole());
			model.addAttribute("inventoryRequest", form);
			
			if (loginUser.getAccountRole().equals(AccountRole.ADMIN)){
				model.addAttribute("storeOptions", inventoryService.getStoreOptions());
				model.addAttribute("bookOptions", inventoryService.getBookOptions());
				model.addAttribute("inventoryRequest", form);
				
				return "inventory/admin/register";
			} else {
				model.addAttribute("bookOptions", inventoryService.getBookOptionsByStoreId(loginUser.getStoreId()));

				return "inventory/store/register";
			}

		}

		form.setInventoryStatus(InventoryStatus.ACTIVE);
		
		
		inventoryService.saveInventory(form, loginUser);
		
		return "redirect:/inventory/list";
	}

	@PostMapping("/edit")
	public String editInventory(
			@Validated(InventoryEditGroup.class) @ModelAttribute("inventoryRequest") InventoryRequestDTO form,
			Errors errors,
			HttpSession session,
			RedirectAttributes ra,
			Model model
			) {

		boolean isStoreIdError = form.getStoreId() == null || form.getStoreId().isBlank();
		
		boolean isAmountZero = form.getAmount() == null || form.getAmount() < 0;
		
		boolean isBookIdError = form.getBookId() == null || form.getBookId().isBlank();
		
		if(errors.hasErrors() || isStoreIdError || isAmountZero || isBookIdError) {
			
			ra.addFlashAttribute("errorMsg", "更新対象の在庫情報が不正です。");
			
			if (isAmountZero) {
				ra.addFlashAttribute("InvalidMsg", "在庫更新時は0以上の値を設定してください。");
			}

			return "redirect:/inventory/list";
		}
		
		inventoryService.modifyInventory(form);
		
		
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
