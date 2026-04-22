package com.integratedbookinfo.system.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

	private final int NUM_PER_PAGE = 10;
	private final BookService bookService;
}
