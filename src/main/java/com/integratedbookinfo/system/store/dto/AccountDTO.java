package com.integratedbookinfo.system.store.dto;

import jakarta.validation.constraints.NotBlank;

import com.integratedbookinfo.system.validationgroup.StoreCreateGroup;
import com.integratedbookinfo.system.validationgroup.StoreEditGroup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

	private int accountId;
	
	@NotBlank(groups = {StoreCreateGroup.class, StoreEditGroup.class})
	private String loginId;
	
	@NotBlank(groups = {StoreCreateGroup.class})
	private String password;
}
