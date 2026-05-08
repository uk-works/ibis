package com.integratedbookinfo.system.store;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.store.dto.StoreCreateRequestDTO;
import com.integratedbookinfo.system.store.dto.StoreEditRequestDTO;
import com.integratedbookinfo.system.store.dto.StoreListResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

	private final StoreMapper storeMapper;
	
	@Override
	public StoreListResponseDTO getStoreList(int page, int numPerPage, String keyword) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public int getTotalPages(int numPerPage, String keyword) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	
	@Override
	public void saveStore(StoreCreateRequestDTO storeCreateRequest) {
		String recentStoreId = storeMapper.selectLastStoreIdForUpdate();
		
		storeCreateRequest.setStoreId(recentStoreId);
		
		storeMapper.insertStore(storeCreateRequest);
	}
	
	@Override
	public void modifyStore(StoreEditRequestDTO storeEditRequest) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	@Override
	public void removeStore(String storeId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
