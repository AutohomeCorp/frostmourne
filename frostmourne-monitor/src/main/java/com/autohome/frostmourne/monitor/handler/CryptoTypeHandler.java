package com.autohome.frostmourne.monitor.handler;

import com.autohome.frostmourne.monitor.tool.AESUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.util.Objects;

/**
 * 加解密mybatis TypeHandler
 *
 * @author limbo
 * @since 2022/9/9 15:08
 */
@MappedTypes({String.class})
public class CryptoTypeHandler implements TypeHandler<String> {

    /**
     * 设置加密
     */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (StringUtils.isNotBlank(parameter)) {
            preparedStatement.setString(i, AESUtils.encrypt(parameter));
        } else {
            preparedStatement.setNull(i, Types.VARCHAR);
        }
    }

    /**
     * 设置解密
     */
    @Override
    public String getResult(ResultSet resultSet, String columnName) throws SQLException {
        return decrypt(resultSet.getString(columnName));
    }

    @Override
    public String getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return decrypt(resultSet.getString(columnIndex));
    }

    @Override
    public String getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return decrypt(callableStatement.getString(columnIndex));
    }

    private String decrypt(String result) {
        if (StringUtils.isNotBlank(result)) {
            String decrypt = AESUtils.decrypt(result);
            return Objects.nonNull(decrypt) ? decrypt : result;
        }
        return result;
    }
}
