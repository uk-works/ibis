package com.integratedbookinfo.system.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;

public class AuthFilter implements Filter {

	public void doFilter(
			ServletRequest request, 
			ServletResponse response, 
			FilterChain chain) throws IOException, ServletException{
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		LoginUserRequestDTO loginUser = (LoginUserRequestDTO) session.getAttribute("loginUser");
		
		if (session == null || loginUser == null) {
			
			res.sendRedirect("/auth/login");
			return;

		}
		
		chain.doFilter(request, response);
	}
}
