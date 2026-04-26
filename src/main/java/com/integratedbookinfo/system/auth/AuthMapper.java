package com.integratedbookinfo.system.auth;

import org.apache.ibatis.annotations.Mapper;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;

/**
 * ユーザーアカウント情報の取得を行うMapper
 * 主にログイン時の認証用データを取得する
 */
@Mapper
public interface AuthMapper {

	public LoginUserRequestDTO selectLoginUserByLoginId(String loginId);
}
