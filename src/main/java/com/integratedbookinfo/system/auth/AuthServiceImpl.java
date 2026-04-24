package com.integratedbookinfo.system.auth;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthMapper authMapper;
	
	@Override
	public LoginUserRequestDTO authenticate(LoginUserRequestDTO loginUserRequest) {
		
		LoginUserRequestDTO loginUser = authMapper.selectLoginUserByLoginId(loginUserRequest.getLoginId());
		
		if (loginUser == null) {
			return null;
		}
		
		if (!BCrypt.checkpw(loginUserRequest.getPassword(), loginUser.getPassword())) {
			return null;
		}
		
		return loginUser;
	}
}
