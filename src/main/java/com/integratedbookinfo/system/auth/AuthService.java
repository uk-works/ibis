package com.integratedbookinfo.system.auth;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;

@Service
public interface AuthService {

	public LoginUserRequestDTO authenticate(LoginUserRequestDTO loginUserRequest);
}
