package com.integratedbookinfo.system.auth.dto;

import jakarta.validation.constraints.NotBlank;

import com.integratedbookinfo.system.shared.enums.AccountRole;

import lombok.Getter;
import lombok.Setter;

/**
 * ログインユーザー情報を保持するDTO
 * 認証処理およびセッション保持に利用する
 */
@Getter
@Setter
public class LoginUserRequestDTO {

	@NotBlank
	private String loginId;
	
	@NotBlank
	private String password;
	private String storeId;
	private String displayName;
	private AccountRole accountRole;
}
