package com.integratedbookinfo.system.store.dto;

import jakarta.validation.constraints.NotBlank;

import com.integratedbookinfo.system.shared.enums.StoreStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreCreateRequestDTO {

	private String storeId;
	
	@NotBlank
	private String storeName;

	@NotBlank
	private String email;
	
	@NotBlank
	private String tel;
	
	@NotBlank
	private String postalCode;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String openingHours;
	
	@NotBlank
	private String closedDays;
	
	private StoreStatus storeStatus;
	
	private String note;
	
	@NotBlank
	private String loginId;
	
	@NotBlank
	private String password;
}
