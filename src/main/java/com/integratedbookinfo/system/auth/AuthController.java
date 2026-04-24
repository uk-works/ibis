package com.integratedbookinfo.system.auth;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("form", new LoginUserRequestDTO());
		return "common/login";
	}
	
	@PostMapping("/login")
	public String login(
			@Validated @ModelAttribute("form") LoginUserRequestDTO form,
			Errors errors,
			HttpSession session) {
		
		System.out.println("ERROR:" + errors.getFieldErrorCount());
		
		List<ObjectError> objList = errors.getAllErrors();
		for(ObjectError obj : objList) {
			System.out.println(obj.toString());
		}
		
		if(errors.hasErrors()) {
			return "common/login";
		}
		
		form.setLoginId("admin0001_user");
		form.setPassword("dev_0011");
		
//		form.setLoginId("store0001_user");
//		form.setPassword("dev_0001");
		
		LoginUserRequestDTO loginUser = authService.authenticate(form);
		
		if (loginUser == null) {
			errors.rejectValue("loginId", "error.login.invalid");
			return "common/login";
		}
		
		loginUser.setPassword(null);
		System.out.println(loginUser.getAccountRole());
		session.setAttribute("loginUser", loginUser);
		
		return "redirect:/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/auth/login";
	}

}
