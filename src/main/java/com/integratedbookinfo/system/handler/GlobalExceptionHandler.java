package com.integratedbookinfo.system.handler;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
	public String handle404(HttpSession session, Model model) {

	    applyLoginState(session, model);

	    return "error/404";
	}
	
	@ExceptionHandler(Exception.class)
	public String handle500(HttpSession session, Model model) {

	    applyLoginState(session, model);

	    return "error/500";
	}
    
    private void applyLoginState(HttpSession session, Model model) {

        LoginUserRequestDTO user =
                (LoginUserRequestDTO) session.getAttribute("loginUser");

        if (user == null || user.getLoginId() == null) {
            model.addAttribute("backUrl", "/auth/login");
            model.addAttribute("backLabel", "ログイン");
        } else {
            model.addAttribute("backUrl", "/dashboard");
            model.addAttribute("backLabel", "ダッシュボード");
        }
    }
}