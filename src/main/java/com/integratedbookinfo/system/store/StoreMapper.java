package com.integratedbookinfo.system.store;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.integratedbookinfo.system.shared.domain.StoreOptionDTO;

@Mapper
public interface StoreMapper {

	public List<StoreOptionDTO> selectStoresHasUnregisteredInventories();
}
