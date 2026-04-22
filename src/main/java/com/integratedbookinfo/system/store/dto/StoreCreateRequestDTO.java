package com.integratedbookinfo.system.store.dto;

import com.integratedbookinfo.system.shared.enums.StoreStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreCreateRequestDTO {

	private String storeId;
	private String storeName;
	private String email;
	private String tel;
	private String postalCode;
	private String address;
	private String openingHours;
	private String closedDays;
	private StoreStatus storeStatus;
	private String note;
	private String id;
	private String password;
}
