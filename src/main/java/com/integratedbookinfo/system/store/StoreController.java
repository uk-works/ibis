package com.integratedbookinfo.system.store;

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

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;
import com.integratedbookinfo.system.shared.enums.StoreStatus;
import com.integratedbookinfo.system.store.dto.AccountDTO;
import com.integratedbookinfo.system.store.dto.StoreCreateRequestDTO;
import com.integratedbookinfo.system.store.dto.StoreDTO;
import com.integratedbookinfo.system.store.dto.StoreEditRequestDTO;
import com.integratedbookinfo.system.validationgroup.StoreCreateGroup;
import com.integratedbookinfo.system.validationgroup.StoreEditGroup;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

	private final int NUM_PER_PAGE = 10;
	private final StoreService storeService;
	

	@GetMapping("/create")
	public String createStore(
			Model model
			) {
		
		StoreCreateRequestDTO storeCreateRequest = new StoreCreateRequestDTO();
		storeCreateRequest.setAccount(new AccountDTO());
		storeCreateRequest.setStore(new StoreDTO());
		
		model.addAttribute("storeCreateRequest", storeCreateRequest);
		model.addAttribute("storeStatuses", StoreStatus.values());
		return "bookstore/store/register";
	}

	@PostMapping("/create")
	public String createStore(
			@Validated(StoreCreateGroup.class) @ModelAttribute("storeCreateRequest") StoreCreateRequestDTO form,
			Errors errors,
			Model model
			) {
		
		if(errors.hasErrors()) {
			model.addAttribute("storeCreateRequest", form);
			model.addAttribute("storeStatuses", StoreStatus.values());
			return "bookstore/store/register";
		}
		
		
		storeService.saveStore(form);
		
		return "redirect:/auth/login";
	}
	
	@GetMapping("/edit")
	public String editStore(HttpSession session, Model model) {
		
		LoginUserRequestDTO loginUser = getLoginUser(session);
		model.addAttribute("displayName", loginUser.getDisplayName());
		model.addAttribute("accountRole", loginUser.getAccountRole());		
		model.addAttribute("storeEditRequest", storeService.getStoreEdit(loginUser.getStoreId()));
		model.addAttribute("storeStatuses", StoreStatus.values());
		
		return "bookstore/store/edit";
	}

	@PostMapping("/edit")
	public String editStore(
			@Validated(StoreEditGroup.class) @ModelAttribute("storeEditRequest") StoreEditRequestDTO form,
			Errors errors,
			HttpSession session,
			Model model
			) {
		
		LoginUserRequestDTO loginUser = getLoginUser(session);
		
		if (errors.hasErrors()) {
			model.addAttribute("storeEditRequest", form);
			model.addAttribute("storeStatuses", StoreStatus.values());
			
			return "bookstore/store/edit";
		}
		
		storeService.modifyStore(form, loginUser);
		
		loginUser.setDisplayName(form.getStore().getStoreName());
		
		session.setAttribute("loginUser", loginUser);
		return "redirect:/dashboard";
	}

	@PostMapping("/delete")
	public String deleteStore(@RequestParam String storeId) {
		return null;
	}
	
	private LoginUserRequestDTO getLoginUser(HttpSession session) {
		LoginUserRequestDTO loginUser = new LoginUserRequestDTO();
		loginUser = (LoginUserRequestDTO) session.getAttribute("loginUser");

		return loginUser;
	}
}
