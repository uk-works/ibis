package com.integratedbookinfo.system.dashboard.dto;

import com.integratedbookinfo.system.shared.enums.StoreStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreStatusDTO {

	private String storeId;
	private String storeName;
	private StoreStatus storeStatus;
}
