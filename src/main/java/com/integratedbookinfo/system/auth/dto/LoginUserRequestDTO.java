package com.integratedbookinfo.system.auth.dto;

import jakarta.validation.constraints.NotBlank;

import com.integratedbookinfo.system.shared.enums.AccountRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequestDTO {

	@NotBlank
	private String loginId;
	
	@NotBlank
	private String password;
	private String displayName;
	private AccountRole accountRole;
}
