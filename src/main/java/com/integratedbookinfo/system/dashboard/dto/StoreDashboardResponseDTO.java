package com.integratedbookinfo.system.dashboard.dto;

import java.util.List;

import com.integratedbookinfo.system.shared.enums.StoreStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * 書店員ダッシュボード画面に表示する情報をまとめたDTO
 *
 * 自店舗のステータス情報および書籍編集提案一覧を保持し、
 * Service層からControllerへ画面表示用データとして受け渡すために使用する
 */
@Getter
@Setter
public class StoreDashboardResponseDTO {

	private StoreStatus storeStatus;
	private List<BookEditProposalSummaryDTO> bookEditProposalSummaries;
}
