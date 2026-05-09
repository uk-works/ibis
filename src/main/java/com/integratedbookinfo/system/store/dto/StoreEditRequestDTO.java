package com.integratedbookinfo.system.store.dto;

import jakarta.validation.Valid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreEditRequestDTO {

	@Valid
	private StoreDTO store;
	
	@Valid
	private AccountDTO account;
}
