package com.integratedbookinfo.system.store;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;
import com.integratedbookinfo.system.store.dto.StoreCreateRequestDTO;
import com.integratedbookinfo.system.store.dto.StoreEditRequestDTO;
import com.integratedbookinfo.system.store.dto.StoreListResponseDTO;

@Service
public interface StoreService {

	public StoreListResponseDTO getStoreList(int page, int numPerPage, String keyword);

	public int getTotalPages(int numPerPage, String keyword);

	public StoreEditRequestDTO getStoreEdit(String storeId);

	public void saveStore(StoreCreateRequestDTO storeCreateRequest);

	public void modifyStore(StoreEditRequestDTO storeEditRequest, LoginUserRequestDTO loginUser);

	public void removeStore(String storeId);


}
