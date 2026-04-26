package com.integratedbookinfo.system.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.integratedbookinfo.system.shared.enums.AccountRole;

public class AccountRoleTypeHandler extends BaseTypeHandler<AccountRole> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, AccountRole parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.name().toLowerCase());
	}

	@Override
	public AccountRole getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return convert(rs.getString(columnName));
	}

	@Override
	public AccountRole getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return convert(rs.getString(columnIndex));
	}

	@Override
	public AccountRole getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return convert(cs.getString(columnIndex));
	}

	private AccountRole convert(String value) {
		if (value == null)
			return null;
		return AccountRole.valueOf(value.toUpperCase());

	}
}
