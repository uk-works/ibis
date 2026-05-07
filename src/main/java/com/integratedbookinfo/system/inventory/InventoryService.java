package com.integratedbookinfo.system.inventory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.inventory.dto.AdminInventoryResponseDTO;
import com.integratedbookinfo.system.inventory.dto.InventoryRequestDTO;
import com.integratedbookinfo.system.inventory.dto.StoreInventoryResponseDTO;
import com.integratedbookinfo.system.shared.domain.BookOptionDTO;
import com.integratedbookinfo.system.shared.domain.StoreOptionDTO;

@Service
public interface InventoryService {

	public AdminInventoryResponseDTO getStoreInventoriesByKeyword(int page, int numPerPage, String keyword);

	public StoreInventoryResponseDTO getInventorySummariesByKeyword(int page, int numPerPage, String storeId, String keyword);

	public int getStoreTotalPagesByKeyword(int numPerPage, String keyword);

	public int getInventoryTotalPagesByKeyword(int numPerPage, String storeId, String keyword);

	public List<StoreOptionDTO> getStoreOptions();
	
	public List<BookOptionDTO> getBookOptions();
	
	public void saveInventory(InventoryRequestDTO inventoryRequest);

	public void modifyInventory(InventoryRequestDTO InventoryRequest);

	public void removeInventory(String storeId, String bookId);


}
