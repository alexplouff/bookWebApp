/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.lowlevel;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alex
 */
public interface DatabaseAccessorStrategy {

    public abstract void closeDatabaseConnection() throws ClassNotFoundException, SQLException;

    public abstract void openDatabaseConnection() throws ClassNotFoundException, SQLException;
    
     public abstract void createRecord(String tableName, List<Object> columns, List<Object> values) throws ClassNotFoundException, SQLException;
     
     public abstract void deleteRecords(Object table, Object primaryKeyColumn,
            List<Object> primaryKeys) throws SQLException, ClassNotFoundException;
     
     public abstract void updateRecord(Object table, List<Object> columns, List<Object> values,
            Object primaryKeyColumn, Object primaryKey) throws SQLException, ClassNotFoundException;
     
//     public Map<String, Object> getRecordById(Object tableName,
//            Object column, Object primaryKey) throws ClassNotFoundException, SQLException;
     
     public abstract  List getRecords(Object table) throws ClassNotFoundException, SQLException;
     
     public abstract  List getJoinedRecords(Object table, Object tablePrimaryKey, Object joiningTable, Object joiningTablePK) throws ClassNotFoundException, SQLException;
}
