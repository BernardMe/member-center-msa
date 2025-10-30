//package com.zzjdyf.customer.config;
//
//import com.baomidou.mybatisplus.core.enums.IEnum;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.MappedTypes;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * @author 阿隆
// */
//@MappedTypes(value = {AccountType.class, BusinessType.class, AccountStatus.class, BindingStatus.class})
//public class EnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
//    private final Class<E> type;
//    private final E[] enums;
//
//    public EnumTypeHandler(Class<E> type) {
//        if (type == null) {
//            throw new IllegalArgumentException("Type argument cannot be null");
//        }
//        this.type = type;
//        this.enums = type.getEnumConstants();
//        if (this.enums == null) {
//            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
//        }
//    }
//
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
//        if (type.isAssignableFrom(IEnum.class)) {
//            IEnum<?> e = (IEnum<?>) parameter;
//            if (e.getValue() instanceof Integer) {
//                ps.setInt(i, (Integer) e.getValue());
//                return;
//            }
//            if (e.getValue() instanceof String) {
//                ps.setString(i, (String) e.getValue());
//                return;
//            }
//            ps.setObject(i, e.getValue());
//            return;
//        }
//        ps.setInt(i, parameter.ordinal());
//    }
//
//    @Override
//    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        if (rs.wasNull()) {
//            return null;
//        }
//        if (type.isAssignableFrom(IEnum.class)) {
//            Object value = rs.getObject(columnName, Object.class);
//            for (E item : enums) {
//                IEnum<?> e = (IEnum<?>) item;
//                if (e.getValue().equals(value)) {
//                    return item;
//                }
//            }
//            return null;
//        }
//        int ordinal = rs.getInt(columnName);
//        return ordinal == 0 && rs.wasNull() ? null : this.toOrdinalEnum(ordinal);
//    }
//
//    @Override
//    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        if (rs.wasNull()) {
//            return null;
//        }
//        if (type.isAssignableFrom(IEnum.class)) {
//            Object value = rs.getObject(columnIndex, Object.class);
//            for (E item : enums) {
//                IEnum<?> e = (IEnum<?>) item;
//                if (e.getValue().equals(value)) {
//                    return item;
//                }
//            }
//            return null;
//        }
//        int ordinal = rs.getInt(columnIndex);
//        return ordinal == 0 && rs.wasNull() ? null : this.toOrdinalEnum(ordinal);
//    }
//
//    @Override
//    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        if (cs.wasNull()) {
//            return null;
//        }
//        if (type.isAssignableFrom(IEnum.class)) {
//            Object value = cs.getObject(columnIndex, Object.class);
//            for (E item : enums) {
//                IEnum<?> e = (IEnum<?>) item;
//                if (e.getValue().equals(value)) {
//                    return item;
//                }
//            }
//            return null;
//        }
//        int ordinal = cs.getInt(columnIndex);
//        return ordinal == 0 && cs.wasNull() ? null : this.toOrdinalEnum(ordinal);
//    }
//
//    private E toOrdinalEnum(int ordinal) {
//        try {
//            return this.enums[ordinal];
//        } catch (Exception var3) {
//            throw new IllegalArgumentException("Cannot convert " + ordinal + " to " + this.type.getSimpleName() + " by ordinal value.", var3);
//        }
//    }
//}
