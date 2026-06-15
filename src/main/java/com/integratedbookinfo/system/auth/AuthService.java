package com.integratedbookinfo.system.auth;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;

/**
 * 認証機能を提供するサービスインターフェース
 * ログイン認証処理を定義する
 */
@Service
public interface AuthService {

	public LoginUserRequestDTO authenticate(LoginUserRequestDTO loginUserRequest);
}
