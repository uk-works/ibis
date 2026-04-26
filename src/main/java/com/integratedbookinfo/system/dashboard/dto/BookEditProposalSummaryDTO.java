package com.integratedbookinfo.system.dashboard.dto;

import java.time.LocalDateTime;

import com.integratedbookinfo.system.shared.enums.ProposalStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * 書籍編集提案一覧表示用のサマリーDTO
 *
 * 書籍編集提案の一覧画面およびダッシュボード表示用に、
 * 提案情報を簡略化して保持するDTO
 *
 * 提案ステータス・書籍名・店舗名・日時情報をまとめて扱う
 */
@Getter
@Setter
public class BookEditProposalSummaryDTO {

	private int proposalId;
	private ProposalStatus proposalStatus;
	private String bookTitle;
	private LocalDateTime proposedAt;
	private LocalDateTime updatedAt;
	private String storeName;
}
