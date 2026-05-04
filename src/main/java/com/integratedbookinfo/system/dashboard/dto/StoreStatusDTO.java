package com.integratedbookinfo.system.dashboard.dto;

import com.integratedbookinfo.system.shared.enums.StoreStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * 店舗ステータス情報を保持するDTO
 *
 * 店舗ID・店舗名・ステータス（営業状態など）をまとめ、
 * ダッシュボード画面や一覧画面で表示するために使用する
 */
@Getter
@Setter
public class StoreStatusDTO {

	private String storeId;
	private String storeName;
	private StoreStatus storeStatus;
}
