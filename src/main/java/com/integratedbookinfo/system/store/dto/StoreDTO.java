package com.integratedbookinfo.system.store.dto;

import jakarta.validation.constraints.NotBlank;

import com.integratedbookinfo.system.shared.enums.StoreStatus;
import com.integratedbookinfo.system.validationgroup.StoreCreateGroup;
import com.integratedbookinfo.system.validationgroup.StoreEditGroup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDTO {
	
	private String storeId;
	
	@NotBlank(groups = {StoreCreateGroup.class, StoreEditGroup.class})
	private String storeName;

	@NotBlank(groups = {StoreCreateGroup.class, StoreEditGroup.class})
	private String email;
	
	@NotBlank(groups = {StoreCreateGroup.class, StoreEditGroup.class})
	private String tel;
	
	@NotBlank(groups = {StoreCreateGroup.class, StoreEditGroup.class})
	private String postalCode;
	
	@NotBlank(groups = {StoreCreateGroup.class, StoreEditGroup.class})
	private String address;
	
	@NotBlank(groups = {StoreCreateGroup.class, StoreEditGroup.class})
	private String openingHours;
	
	@NotBlank(groups = {StoreCreateGroup.class, StoreEditGroup.class})
	private String closedDays;
	
	private StoreStatus storeStatus;
	
	private String note;
}
