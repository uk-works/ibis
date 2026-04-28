package com.integratedbookinfo.system.book;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;
import com.integratedbookinfo.system.book.dto.AdminBookEditRequestDTO;
import com.integratedbookinfo.system.book.dto.BookCreateRequestDTO;
import com.integratedbookinfo.system.shared.enums.AccountRole;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

	private final int NUM_PER_PAGE = 10;
	private final BookService bookService;

	@GetMapping("/list")
	public String showBookList(
			@RequestParam(name = "keyword", required = false) String keyword ,
			@RequestParam(name = "page", defaultValue = "1") Integer page, 
			HttpSession session,
			Model model) {

		String pageName = "";
		
		LoginUserRequestDTO loginUser = getLoginUser(session);
		
		model.addAttribute("page", page);
		
		model.addAttribute("displayName", loginUser.getDisplayName());
		model.addAttribute("accountRole", loginUser.getAccountRole());
		
		if(loginUser.getAccountRole().equals(AccountRole.ADMIN)) {
			model.addAttribute("books", bookService.getBookList(page, NUM_PER_PAGE, loginUser.getAccountRole(), null, keyword).getBooks());
			model.addAttribute("totalPages", bookService.getTotalPages(NUM_PER_PAGE, loginUser.getAccountRole(), null, keyword));
		
			pageName = "book/admin/list";
		} else if(loginUser.getAccountRole().equals(AccountRole.STORE)){
			model.addAttribute("books", bookService.getBookList(page, NUM_PER_PAGE, loginUser.getAccountRole(), loginUser.getStoreId(), keyword).getBooks());
			model.addAttribute("totalPages", bookService.getTotalPages(NUM_PER_PAGE, loginUser.getAccountRole(), loginUser.getStoreId(), keyword));

			pageName = "book/store/list";
		}

		return pageName;
	}

	@GetMapping("/create")
	public String createBook(Model model, HttpSession session) {
		
		LoginUserRequestDTO loginUser = getLoginUser(session);
		String pageName = "";
		
		model.addAttribute("displayName", loginUser.getDisplayName());
		model.addAttribute("accountRole", loginUser.getAccountRole());

		model.addAttribute("bookCreateRequest", new BookCreateRequestDTO());
		
		if(loginUser.getAccountRole().equals(AccountRole.ADMIN)) {
			pageName = "book/admin/create";
		} else if(loginUser.getAccountRole().equals(AccountRole.STORE)){

			pageName = "book/store/create";
		}

		
		return pageName;
	}

	@PostMapping("/create")
	public String createBook(
			@Valid @ModelAttribute BookCreateRequestDTO form,
			Errors errors,
			Model model) {

		System.out.println(form);
		
		return "redirect:/book/list";
	}

	@GetMapping("/edit")
	public String editBook(Model model) {
		return "";
	}

	@PostMapping("/edit")
	public String editBook(
			@Valid @ModelAttribute AdminBookEditRequestDTO form,
			Errors errors,
			Model model) {
		return "";
	}

	@PostMapping("/delete")
	public String deleteBook(@RequestParam String bookId) {
		return "";
	}
	
	private LoginUserRequestDTO getLoginUser(HttpSession session) {
		LoginUserRequestDTO loginUser = new LoginUserRequestDTO();
		loginUser = (LoginUserRequestDTO) session.getAttribute("loginUser");

		return loginUser;
	}
	
}
