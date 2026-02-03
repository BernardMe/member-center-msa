package com.cheshun.mall.domain.entity;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 2.3 持久层使用Money与数据库交互
 * 这里，我们以 MybatisPlus 为例。
 * 通过扩展 mybatis 的 TypeHandler 来实现。
 *
 * 2.3.2 如果数据库里的金额字段是以“分”为单位的整形数据。那么，我们使用 MoneyFenTypeHandler
 */

/**
 * 实现Money与数据库的“分”金额字段的互相映射
 */
@Component
@MappedTypes(value = Money.class)
public class MoneyFenTypeHandler extends BaseTypeHandler<Money> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Money parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter.getFen());
    }

    @Override
    public Money getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Money.ofFen(rs.getLong(columnName));
    }

    @Override
    public Money getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Money.ofFen(rs.getLong(columnIndex));
    }

    @Override
    public Money getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Money.ofFen(cs.getLong(columnIndex));
    }
}
