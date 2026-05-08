package com.integratedbookinfo.system.store.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

	private int accountId;
	
	@NotBlank
	private String loginId;
	
	@NotBlank
	private String password;
}
