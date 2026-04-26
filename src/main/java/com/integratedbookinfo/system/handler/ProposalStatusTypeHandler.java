package com.integratedbookinfo.system.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.integratedbookinfo.system.shared.enums.ProposalStatus;

public class ProposalStatusTypeHandler extends BaseTypeHandler<ProposalStatus> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ProposalStatus parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.name().toLowerCase());
	}

	@Override
	public ProposalStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return convert(rs.getString(columnName));
	}

	@Override
	public ProposalStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return convert(rs.getString(columnIndex));
	}

	@Override
	public ProposalStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return convert(cs.getString(columnIndex));
	}

	private ProposalStatus convert(String value) {
		if (value == null)
			return null;
		return ProposalStatus.valueOf(value.toUpperCase());

	}
}
