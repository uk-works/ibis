package com.integratedbookinfo.system.store;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.integratedbookinfo.system.shared.domain.StoreOptionDTO;
import com.integratedbookinfo.system.store.dto.StoreCreateRequestDTO;
import com.integratedbookinfo.system.store.dto.StoreEditRequestDTO;
import com.integratedbookinfo.system.store.dto.StoreSummaryDTO;

@Mapper
public interface StoreMapper {

	public List<StoreSummaryDTO> selectStoreList(@Param("offset") int offset, @Param("limit")int limit, @Param("keyword") String keyword);

	public Long countStoreRecord(String keyword);

	public int insertStore(StoreCreateRequestDTO StoreCreateRequest);

	public int updateStore(StoreEditRequestDTO storeEditRequest);

	public int deleteStore(String StoreId);


	
	
	public List<StoreOptionDTO> selectStoresHasUnregisteredInventories();
}
