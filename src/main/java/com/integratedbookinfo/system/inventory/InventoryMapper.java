package com.integratedbookinfo.system.inventory;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryMapper {

	public int deleteInventoryByBookId(String bookId);
}
