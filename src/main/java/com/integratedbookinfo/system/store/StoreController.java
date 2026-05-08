package com.integratedbookinfo.system.store;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.integratedbookinfo.system.shared.enums.StoreStatus;
import com.integratedbookinfo.system.store.dto.StoreCreateRequestDTO;
import com.integratedbookinfo.system.store.dto.StoreEditRequestDTO;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

	private final int NUM_PER_PAGE = 10;
	private final StoreService storeService;
	
	@GetMapping("/list")
	public String showStoreList(
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "page", defaultValue = "1") Integer page, 
			Model model) {
		return null;
	}

	@GetMapping("/create")
	public String createStore(
			Model model
			) {
		
		model.addAttribute("storeCreateRequest", new StoreCreateRequestDTO());
		model.addAttribute("storeStatuses", StoreStatus.values());
		return "bookstore/store/register";
	}

	@PostMapping("/create")
	public String createStore(
			@Valid @ModelAttribute("storeCreateRequest") StoreCreateRequestDTO form,
			Errors errors,
			Model model
			) {
		
		if(errors.hasErrors()) {
			model.addAttribute("storeCreateRequest", form);
			model.addAttribute("storeStatuses", StoreStatus.values());
			return "bookstore/store/register";
		}
		
		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(form));
		
		storeService.saveStore(form);
		
		return "redirect:/auth/login";
	}
	
	@GetMapping("/edit")
	public String editStore(Model model) {
		return null;
	}

	@PostMapping("/edit")
	public String editStore(
			@Valid @ModelAttribute StoreEditRequestDTO form,
			Errors errors,
			Model model
			) {
		return null;
	}

	@PostMapping("/delete")
	public String deleteStore(@RequestParam String storeId) {
		return null;
	}
}
