package com.integratedbookinfo.system.inventory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

	private final int NUM_PER_PAGE = 10;
	private final InventoryService inventoryService;
}
