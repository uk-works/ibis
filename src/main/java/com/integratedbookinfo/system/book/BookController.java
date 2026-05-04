package com.integratedbookinfo.system.book;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;
import com.integratedbookinfo.system.book.dto.BookCreateRequestDTO;
import com.integratedbookinfo.system.book.dto.BookEditRequestDTO;
import com.integratedbookinfo.system.book.dto.StoreBookDTO;
import com.integratedbookinfo.system.shared.domain.BookDTO;
import com.integratedbookinfo.system.shared.enums.AccountRole;
import com.integratedbookinfo.system.shared.enums.BookSize;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

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

		BookCreateRequestDTO bookCreateRequest = new BookCreateRequestDTO();
		bookCreateRequest.setBook(new BookDTO());
		bookCreateRequest.setStoreBook(new StoreBookDTO());
		
		model.addAttribute("bookCreateRequest", bookCreateRequest);
		
		model.addAttribute("publishers", bookService.getPublishers());
		model.addAttribute("genres", bookService.getGenres());
		model.addAttribute("stores", bookService.getStoreOptions());
		model.addAttribute("bookSizes", BookSize.values());
		
		if(loginUser.getAccountRole().equals(AccountRole.ADMIN)) {
			pageName = "book/admin/create";
		} else if(loginUser.getAccountRole().equals(AccountRole.STORE)){
			pageName = "book/store/create";
		}
		
		return pageName;
	}

	@PostMapping("/create")
	public String createBook(
			@Validated @ModelAttribute("bookCreateRequest") BookCreateRequestDTO form,
			Errors errors,
			HttpSession session,
			Model model) {
		
		
		LoginUserRequestDTO loginUser = getLoginUser(session);
		
		MultipartFile imageFile = form.getBook().getCoverImageFile();
		
		if (errors.hasErrors() || isInvalidImage(imageFile)) {
			
			if (isInvalidImage(imageFile)) {
				errors.rejectValue("book.coverImageFile", "error.book.image.invalid");
			}

			model.addAttribute("displayName", loginUser.getDisplayName());
			model.addAttribute("accountRole", loginUser.getAccountRole());

			model.addAttribute("publishers", bookService.getPublishers());
			model.addAttribute("genres", bookService.getGenres());
			model.addAttribute("stores", bookService.getStoreOptions());
			model.addAttribute("bookSizes", BookSize.values());
			model.addAttribute("bookCreateRequest", form);
			
			
			if (loginUser.getAccountRole().equals(AccountRole.ADMIN)) {

				return "book/admin/create";
			} else if(loginUser.getAccountRole().equals(AccountRole.STORE)){

				return "book/store/create";
			}
		}

		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(form.getBook()));
		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(form.getStoreBook()));

		// Serive呼び出し
		bookService.saveBook(form, loginUser);
		
		// 画像登録処理
		return "redirect:/book/list";
	}

	@GetMapping("/edit/{bookId}")
	public String editBook(@PathVariable String bookId, Model model, HttpSession session) {
		
		LoginUserRequestDTO loginUser = getLoginUser(session);

		String pageName = "";
		
		model.addAttribute("displayName", loginUser.getDisplayName());
		model.addAttribute("accountRole", loginUser.getAccountRole());
		
		model.addAttribute("publishers", bookService.getPublishers());
		model.addAttribute("genres", bookService.getGenres());
		model.addAttribute("bookSizes", BookSize.values());

		if (loginUser.getAccountRole().equals(AccountRole.ADMIN)) {
			model.addAttribute("storeOptions", bookService.getStoreOptionsByBookId(bookId));
			model.addAttribute("storeBooks", bookService.getStoreBooksByBookId(bookId));
			model.addAttribute("bookEditRequest", bookService.getBookAndStoreBookForAdmin(loginUser.getAccountRole(), bookId));

			pageName = "book/admin/edit";
		} else if(loginUser.getAccountRole().equals(AccountRole.STORE)){
			model.addAttribute("bookEditRequest", bookService.getBookAndStoreBookForStore(loginUser.getAccountRole(), bookId, loginUser.getStoreId()));

			pageName = "book/store/edit";
		}
		
		return pageName;
	}

	@PostMapping("/edit/{bookId}")
	public String editBook(
			@PathVariable String bookId,
			@Valid @ModelAttribute("bookEditRequest") BookEditRequestDTO form,
			Errors errors,
			HttpSession session,
			Model model) {

		LoginUserRequestDTO loginUser = getLoginUser(session);
		
		MultipartFile imageFile = form.getBook().getCoverImageFile();
		
		List<ObjectError> objList = errors.getAllErrors();
		for(ObjectError obj : objList) {
			System.out.println(obj.toString());
		}
		
		if (errors.hasErrors() || isInvalidImage(imageFile)) {
			
			if (isInvalidImage(imageFile)) {
				errors.rejectValue("book.coverImageFile", "error.book.image.invalid");
			}
			
			model.addAttribute("displayName", loginUser.getDisplayName());
			model.addAttribute("accountRole", loginUser.getAccountRole());
			
			model.addAttribute("publishers", bookService.getPublishers());
			model.addAttribute("genres", bookService.getGenres());
			model.addAttribute("bookSizes", BookSize.values());
			
			if (loginUser.getAccountRole().equals(AccountRole.ADMIN)) {
				model.addAttribute("storeOptions", bookService.getStoreOptionsByBookId(bookId));
				model.addAttribute("storeBooks", bookService.getStoreBooksByBookId(bookId));
				model.addAttribute("bookEditRequest", form);

				return "book/admin/edit";
			} else if(loginUser.getAccountRole().equals(AccountRole.STORE)){
				model.addAttribute("bookEditRequest", form);

				return "book/store/edit";
			}
		}
		
		bookService.modifyBook(loginUser.getAccountRole(), form.getBook(), form.getStoreBook());
		
		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(form.getBook()));
		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(form.getStoreBook()));
		
		return "redirect:/book/list";
	}

	@PostMapping("/delete/{bookId}")
	public String deleteBook(@PathVariable String bookId, HttpSession session) {
		
		bookService.removeBook(bookId);
		
		return "redirect:/book/list";
	}
	
	private LoginUserRequestDTO getLoginUser(HttpSession session) {
		LoginUserRequestDTO loginUser = new LoginUserRequestDTO();
		loginUser = (LoginUserRequestDTO) session.getAttribute("loginUser");

		return loginUser;
	}
	
	private boolean isInvalidImage(MultipartFile file) {
		
		// 画像未選択時 チェック終了
		if(file == null || file.isEmpty()) {
			return false;
		}
		
		// MIMEチェック
	    String contentType = file.getContentType();

	    boolean mimeOk = contentType != null && contentType.startsWith("image/");

	    // 拡張子チェック（メイン）
	    String filename = file.getOriginalFilename();
	    if (filename == null) return true;

	    String lower = filename.toLowerCase();
	    boolean extOk = lower.endsWith(".jpg")
	                 || lower.endsWith(".jpeg")
	                 || lower.endsWith(".png");

	    return !(mimeOk && extOk);
	    
	}
}
