/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.lowlevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alex
 */
public class SQL_Accessor implements DatabaseAccessorStrategy {

    private SQL_Data_Provider connectionInfo;
    private Connection sql_conn;
    private StringBuffer sBuffer;

    public SQL_Accessor(SQL_Data_Provider connectionInfo) {
        setConnectionInfo(connectionInfo);
    }

    public SQL_Data_Provider getConInfo() {
        return connectionInfo;
    }

    public final void setConnectionInfo(SQL_Data_Provider connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    @Override
    public void openDatabaseConnection() throws ClassNotFoundException, SQLException {

        try {
            Class.forName(connectionInfo.getDriverName());
            sql_conn = DriverManager.getConnection(
                    connectionInfo.getDbURL(),
                    connectionInfo.getUserName(),
                    connectionInfo.getPassword());
            System.out.println("Connection Is Open");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void closeDatabaseConnection() throws ClassNotFoundException, SQLException {
        sql_conn.close();
        System.out.println("Connection Is Closed");
    }

    @Override
    public void deleteRecords(Object db, Object table, Object primaryKeyColumn,
            List<Object> primaryKeys) throws SQLException, ClassNotFoundException {
        try {
            openDatabaseConnection();
            PreparedStatement pstmt;
            String query = "DELETE FROM " + db + "." + table + " WHERE " + primaryKeyColumn + "= ?";
            for (Object obj : primaryKeys) {
                pstmt = sql_conn.prepareStatement(query);
                pstmt.setObject(1, obj.toString());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Accessor failed");
        }

    }

    @Override
    public void createRecord(String tableName, List<Object> columns, List<Object> values) throws ClassNotFoundException, SQLException {

        StringBuilder sb = new StringBuilder("INSERT INTO " + tableName + " (");
        try {
            openDatabaseConnection();
            for (Object col : columns) {
                sb.append(col).append(",");
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            sb.append(") VALUES (");
            for (Object val : values) {
                sb.append("?,");
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            sb.append(")");

            PreparedStatement pStmt = sql_conn.prepareStatement(sb.toString());

            for (int i = 0; i < values.size(); i++) {
                pStmt.setObject(i + 1, values.get(i));
            }

            pStmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException se) {
            se.getLocalizedMessage();
        } finally {
            closeDatabaseConnection();
        }
    }

    @Override
    public void updateRecord(Object table, List<Object> columns, List<Object> values,
            Object primaryKeyColumn, Object primaryKey) throws SQLException, ClassNotFoundException {

        PreparedStatement pstmt = null;
        StringBuilder sb = new StringBuilder("UPDATE " + table + " SET ");

        try {
            openDatabaseConnection();

            for (Object col : columns) {
                sb.append(col).append("=?, ");
            }

            sb = sb.deleteCharAt(sb.length() - 2); // deletes final extra white space (" ") and comma
            sb.append("WHERE ").append(primaryKeyColumn).append(" = ").append(primaryKey);

            pstmt = sql_conn.prepareStatement(sb.toString());

            for (int i = 0; i < values.size(); i++) {
                pstmt.setObject(i + 1, values.get(i));
            }

            pstmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.getLocalizedMessage();
            System.out.println("Failed In 'upadteRecords' method");
        } finally {
            pstmt.close();
            closeDatabaseConnection();
        }

    }

    @Override
    public List getRecords(Object table) throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM " + table + " JOIN ";
        List<Map<String, Object>> list = null;
        Map map = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData md = null;
        try {
            openDatabaseConnection();
            stmt = sql_conn.createStatement();
            rs = stmt.executeQuery(query);
            md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            list = new ArrayList();

            while (rs.next()) {
                map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    try {
                        map.put(md.getColumnName(i), rs.getObject(i));
                    } catch (NullPointerException npe) {

                    }
                }
                list.add(map);
            }

        } catch (SQLException | ClassNotFoundException se) {
            se.getLocalizedMessage();
        } finally {
            stmt.close();
            closeDatabaseConnection();
        }
        return list;
    }

    @Override
    public List getJoinedRecords(Object db,Object table, Object tablePrimaryKey,
            Object joiningTable, Object joiningTablePK) throws ClassNotFoundException, SQLException {
        List<Map<String, Object>> list = null;
        Map map = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData md = null;
        try {
            openDatabaseConnection();
            stmt = sql_conn.createStatement();
            rs = stmt.executeQuery(getJoinBuilder(db, table,tablePrimaryKey,joiningTable,joiningTablePK).toString());
            md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            list = new ArrayList();

            while (rs.next()) {
                map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    try {
                        map.put(md.getColumnName(i), rs.getObject(i));
                    } catch (NullPointerException npe) {

                    }
                }
                list.add(map);
            }

        } catch (SQLException | ClassNotFoundException se) {
            se.getLocalizedMessage();
            System.out.println("failed");
        } finally {
            stmt.close();
            closeDatabaseConnection();
        }
        System.out.println("List size:" +list.size());
        return list;
    }

    public StringBuilder getJoinBuilder(Object dbName, Object table, Object tablePrimaryKey, Object joiningTable, Object joiningForeignKey) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(dbName)
                .append(".").append(table).append(" JOIN ").append(dbName).append(".").append(joiningTable)
                .append(" ON ").append(joiningTable).append(".").append(joiningForeignKey)
                .append("=").append(table).append(".").append(joiningForeignKey);
        System.out.println(sb.toString());
        return sb;
    }

//    @Override
//    public Map<String, Object> getRecordById(Object tableName,
//            Object column, Object primaryKey)
//            throws ClassNotFoundException, SQLException {
//
//        Map<String, Object> record = null;
//        PreparedStatement pStmt = null;
//        ResultSetMetaData metaData = null;
//        ResultSet rs = null;
//        String query = "SELECT * FROM " + tableName + " WHERE " + column + "=?";
//
//        try {
//            openDatabaseConnection();
//            pStmt = sql_conn.prepareStatement(query);
//            pStmt.setObject(1, primaryKey);
//            pStmt.executeQuery();
//            rs = pStmt.executeQuery();
//            metaData = rs.getMetaData();
//            int colCount = metaData.getColumnCount();
//
//            if (rs.next()) {
//                record = new HashMap<>();
//                for (int i = 1; i < colCount; i++) {
//                    record.put(metaData.getColumnName(i), rs.getObject(i));
//                }
//            }
//
//        } catch (IllegalArgumentException | ClassNotFoundException | SQLException e) {
//            e.getLocalizedMessage();
//        } finally {
//            pStmt.close();
//            closeDatabaseConnection();
//        }
//        return record;
//    }
    public static void main(String[] args) throws Exception {
        SQL_Data_Provider dp = new SQL_Data_Provider("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/Client?zeroDateTimeBehavior=convertToNull",
                "root", "root");
        SQL_Accessor accessor = new SQL_Accessor(dp);
        accessor.openDatabaseConnection();
        accessor.closeDatabaseConnection();

//        System.out.println(accessor.getJoinedRecords("Library.Book", "BookID", "Library.Author", "AuthorID"));
    }
}
