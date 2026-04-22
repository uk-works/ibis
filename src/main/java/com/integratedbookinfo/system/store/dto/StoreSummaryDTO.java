package com.integratedbookinfo.system.store.dto;

import com.integratedbookinfo.system.shared.enums.StoreStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreSummaryDTO {

	private String storeId;
	private String storeName;
	private StoreStatus storeStatus;
	private String tel;
	private String email;
}
