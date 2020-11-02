/**
 *
 */
package net.scode.commons.db;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * 纯JDBC原生操作封装
 *
 *
 */
public class JdbcUtil implements Closeable {

    private Connection conn = null;

    private PreparedStatement prepStmt = null;

    /**
     * 构造方式1
     *
     * @param con
     */
    public JdbcUtil(Connection con) {
        this.conn = con;
    }

    /**
     * 构造方式2
     *
     * @param dataSource
     */
    public JdbcUtil(DataSource dataSource) {
        try {
            this.conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造方式3
     *
     * @param driver
     * @param url
     * @param user
     * @param password
     */
    public JdbcUtil(String driver, String url, String user, String password) {
        try {
            if (conn == null) {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 预编译SQL
     *
     * @param sql
     * @throws SQLException
     */
    public void prepareStatement(String sql) throws SQLException {
        prepStmt = conn.prepareStatement(sql);
    }

    /**
     * 绑定变量赋值
     *
     * @param index
     * @param value
     * @throws SQLException
     */
    public void setString(int index, String value) throws SQLException {
        prepStmt.setString(index, value);
    }

    /**
     * 绑定变量赋值
     *
     * @param index
     * @param value
     * @throws SQLException
     */
    public void setInt(int index, int value) throws SQLException {
        prepStmt.setInt(index, value);
    }

    /**
     * 绑定变量赋值
     *
     * @param index
     * @param value
     * @throws SQLException
     */
    public void setObject(int index, Object value) throws SQLException {
        if (value == null) {
            value = "";
        }
        prepStmt.setObject(index, value);
    }

    /**
     * 执行查询得到结果集
     *
     * @return
     * @throws SQLException
     */
    public ResultSet executeRs() throws SQLException {
        return prepStmt.executeQuery();
    }

    /**
     * 执行单个查询得到Map
     *
     * @return
     * @throws SQLException
     */
    public Map<String, Object> executeMap() throws SQLException {
        return resultset2Map(executeRs());
    }

    /**
     * 执行列表查询
     *
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> executeList() throws SQLException {
        return resultset2List(executeRs());
    }

    /**
     * 执行update操作，包括插入、修改、删除
     *
     * @return
     * @throws SQLException
     */
    public int executeUpdate() throws SQLException {
        if (prepStmt != null) {
            return prepStmt.executeUpdate();
        } else {
            return -1;
        }
    }

    /**
     * 执行插入操作，返回插入的主键值(int)
     *
     * @return
     * @throws SQLException
     */
    public int executeInsert() throws SQLException {
        if (executeUpdate() > 0) {
            return getInsertId();
        } else {
            return -1;
        }
    }

    /**
     * 结果集转换
     *
     * @param rs
     * @return
     */
    private Map<String, Object> resultset2Map(ResultSet rs) {
        Map<String, Object> row = null;
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            if (rs.next()) {
                row = MapUtil.newHashMap();
                for (int i = 1; i <= columnCount; i++) {
                    Object oTemp = rs.getObject(i);
                    row.put(meta.getColumnName(i), oTemp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeRs(rs);
        }
        return row;
    }

    /**
     * 结果集转换
     *
     * @param rs
     * @return
     */
    private List<Map<String, Object>> resultset2List(ResultSet rs) {
        List<Map<String, Object>> rowlist = CollectionUtil.newArrayList();
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = MapUtil.newHashMap();
                for (int i = 1; i <= columnCount; i++) {
                    Object oTemp = rs.getObject(i);
                    if (oTemp == null) {
                        oTemp = "";
                    }
                    row.put(meta.getColumnName(i), oTemp);
                }
                rowlist.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeRs(rs);
        }
        return rowlist;
    }

    /**
     * 返回插入的主键id值
     *
     * @return
     * @throws SQLException
     */
    private int getInsertId() throws SQLException {
        int id = -1;
        ResultSet rs = null;
        try {
            rs = prepStmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeRs(rs);
        }
        return id;
    }

    /**
     * 关闭ResultSet
     *
     * @param rs
     */
    public void closeRs(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
    }

    /**
     * 关闭Statement
     */
    public void closeStmt() {
        if (prepStmt != null) {
            try {
                prepStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            prepStmt = null;
        }
    }

    /**
     * 关闭整个连接，包括Statement
     */
    @Override
    public void close() {
        try {
            closeStmt();
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
