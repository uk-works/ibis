package com.integratedbookinfo.system.inventory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.integratedbookinfo.system.book.BookMapper;
import com.integratedbookinfo.system.inventory.dto.AdminInventoryResponseDTO;
import com.integratedbookinfo.system.inventory.dto.InventoryRequestDTO;
import com.integratedbookinfo.system.inventory.dto.StoreInventoryResponseDTO;
import com.integratedbookinfo.system.shared.domain.BookOptionDTO;
import com.integratedbookinfo.system.shared.domain.StoreOptionDTO;
import com.integratedbookinfo.system.store.StoreMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

	private final InventoryMapper inventoryMapper;
	private final BookMapper bookMapper;
	private final StoreMapper storeMapper;
	
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
	
	/**
	 * TODO（優先順位：低）DB取得メソッドはbookMapperからstoreMapperに変更する
	 */
	@Override
	public List<StoreOptionDTO> getStoreOptions() {
	
		return storeMapper.selectStoresHasUnregisteredInventories();
	}
	
	@Override
	public List<BookOptionDTO> getBookOptions() {

		return bookMapper.selectBookOptions();
	}
	
	@Override
	public void saveInventory(InventoryRequestDTO inventoryRequest) {
		// 新規在庫追加の場合 or 廃止された在庫復活の場合で処理が分かれる
		int countInventory = inventoryMapper.countInventoryByStoreIdAndBookId(inventoryRequest.getStoreId(), inventoryRequest.getBookId());
		int countUpdateResumeInventory = 0;
		int countInsertInventory = 0;
		
		if (countInventory == 1) {
			countUpdateResumeInventory = inventoryMapper.updateResumeInventory(inventoryRequest);
		} else {
			countInsertInventory = inventoryMapper.insertInventory(inventoryRequest);
		}
		
		System.out.println("updateResumeInventory()：" + countUpdateResumeInventory + "件");
		System.out.println("insertInventory()：" + countInsertInventory + "件");
	}
	
	@Override
	public void modifyInventory(InventoryRequestDTO InventoryRequest) {
		
		inventoryMapper.updateInventory(InventoryRequest);
	}
	
	@Override
	public void removeInventory(String storeId, String bookId) {
		
		inventoryMapper.deleteInventoryByStoreIdAndBookId(storeId, bookId);
		/* TODO 削除必要↓ */
		// bookMapper.deleteStoreBookByBookIdAndStoreId(bookId, storeId);
	}
}
