package com.integratedbookinfo.system.auth.dto;

import com.integratedbookinfo.system.shared.enums.AccountRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequestDTO {

	private String loginId;
	private String password;
	private String displayName;
	private AccountRole accountRole;
}
