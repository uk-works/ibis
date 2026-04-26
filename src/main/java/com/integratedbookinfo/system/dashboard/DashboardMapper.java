package com.integratedbookinfo.system.dashboard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.integratedbookinfo.system.dashboard.dto.BookEditProposalSummaryDTO;
import com.integratedbookinfo.system.dashboard.dto.StoreStatusDTO;
import com.integratedbookinfo.system.shared.enums.StoreStatus;

/**
 * ダッシュボード画面表示に必要なデータを取得するMapper
 *
 * 管理者・書店員それぞれの権限に応じて、
 * 書籍編集提案情報および店舗ステータス情報をDBから取得する
 */
@Mapper
public interface DashboardMapper {

	public List<BookEditProposalSummaryDTO> selectAdminBookEditProposalList();

	public List<StoreStatusDTO> selectAdminStoreStatusList();

	public List<BookEditProposalSummaryDTO> selectStoreBookEditProposalList(String storeId);

	public StoreStatus selectStoreStoreStatus(String storeId);

}
