package com.integratedbookinfo.system.inventory;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.inventory.dto.AdminInventoryResponseDTO;
import com.integratedbookinfo.system.inventory.dto.InventoryRequestDTO;
import com.integratedbookinfo.system.inventory.dto.StoreInventoryResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

	private final InventoryMapper inventoryMapper;
	
	@Override
	public AdminInventoryResponseDTO getStoreInventoriesByKeyword(int page, int numPerPage, String keyword) {
		int offset = numPerPage * (page - 1);
		
		AdminInventoryResponseDTO adminInventoryResponse = new AdminInventoryResponseDTO();
		adminInventoryResponse.setStoreInventories(inventoryMapper.selectStoreInventoriesByKeyword(offset, numPerPage, keyword));

		return adminInventoryResponse;
	}
	
	@Override
	public StoreInventoryResponseDTO getInventorySummariesByKeyword(int page, int numPerPage, String storeId,
			String keyword) {
		int offset = numPerPage * (page - 1);
		
		StoreInventoryResponseDTO storeInventoryResponse = new StoreInventoryResponseDTO();
		storeInventoryResponse.setInventorySummaries(inventoryMapper.selectInventorySummariesByKeyword(offset, numPerPage, storeId, keyword));
		
		return storeInventoryResponse;
	}
	
	@Override
	public int getStoreTotalPagesByKeyword(int numPerPage, String keyword) {
		double totalNum = (double) inventoryMapper.countStoresByKeyword(keyword);

		return (int) Math.ceil(totalNum / numPerPage);
	}
	
	@Override
	public int getInventoryTotalPagesByKeyword(int numPerPage, String storeId, String keyword) {
		double totalNum = (double) inventoryMapper.countInventorySummariesByKeyword(keyword);

		return (int) Math.ceil(totalNum / numPerPage);
	}
	
	@Override
	public void saveInventory(InventoryRequestDTO inventoryRequestDto) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	@Override
	public void modifyInventory(InventoryRequestDTO InventoryRequestDto) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	@Override
	public void removeInventory(String storeId, String bookId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
