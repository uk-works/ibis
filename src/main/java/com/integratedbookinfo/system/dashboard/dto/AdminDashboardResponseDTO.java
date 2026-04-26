package com.integratedbookinfo.system.dashboard.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理者ダッシュボード画面に表示する情報をまとめたDTO
 *
 * 各店舗のステータス情報および書籍編集提案情報を保持し、
 * 画面表示用データとしてService層からControllerへ受け渡すために使用する
 */
@Getter
@Setter
public class AdminDashboardResponseDTO {

	private List<StoreStatusDTO> storeStatuses;
	private List<BookEditProposalSummaryDTO> bookEditProposalSummaries;
}
