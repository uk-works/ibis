package com.integratedbookinfo.system.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.integratedbookinfo.system.shared.enums.BookSize;

public class BookSizeTypeHandler extends BaseTypeHandler<BookSize> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, BookSize parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.name().toLowerCase());
	}

	@Override
	public BookSize getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return convert(rs.getString(columnName));
	}

	@Override
	public BookSize getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return convert(rs.getString(columnIndex));
	}

	@Override
	public BookSize getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return convert(cs.getString(columnIndex));
	}

	private BookSize convert(String value) {
		if (value == null)
			return null;
		return BookSize.valueOf(value.toUpperCase());

	}
}
