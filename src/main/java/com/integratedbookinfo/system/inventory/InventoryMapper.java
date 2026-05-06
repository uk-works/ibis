package com.integratedbookinfo.system.inventory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.integratedbookinfo.system.inventory.dto.InventoryRequestDTO;
import com.integratedbookinfo.system.inventory.dto.InventorySummaryDTO;
import com.integratedbookinfo.system.inventory.dto.StoreInventoryDTO;

@Mapper
public interface InventoryMapper {

	public List<StoreInventoryDTO> selectStoreInventoriesByKeyword(@Param("offset") int offset, @Param("limit")int limit, @Param("keyword") String keyword);

	public List<InventorySummaryDTO> selectInventorySummariesByKeyword(@Param("offset") int offset, @Param("limit")int limit, @Param("storeId") String storeId, @Param("keyword") String keyword);

	public Long countStoresByKeyword(String keyword);
	
	public Long countInventorySummariesByKeyword(String keyword);

	public int insertInventory(InventoryRequestDTO inventoryRequestDto);

	public int updateInventory(InventoryRequestDTO inventoryRequestDto);

	public int deleteInventory(String storeId, String bookId);


	
	public int deleteInventoryByBookId(String bookId);
}
