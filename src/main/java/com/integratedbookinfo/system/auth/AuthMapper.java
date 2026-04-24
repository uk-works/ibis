package com.integratedbookinfo.system.auth;

import org.apache.ibatis.annotations.Mapper;

import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;

@Mapper
public interface AuthMapper {

	public LoginUserRequestDTO selectLoginUserByLoginId(String loginId);
}
