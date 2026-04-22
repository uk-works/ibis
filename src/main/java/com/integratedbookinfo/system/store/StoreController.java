package com.integratedbookinfo.system.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

	private final int NUM_PER_PAGE = 10;
	private final StoreService storeService;
}
