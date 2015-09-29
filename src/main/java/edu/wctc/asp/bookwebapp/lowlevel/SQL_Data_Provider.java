/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.lowlevel;


/**
 *
 * @author alex
 */
public class SQL_Data_Provider {
    
    private String driverName, dbURL, userName, password;

    public SQL_Data_Provider(String driverName, String dbURL, String userName, String password) {
        setDriverName(driverName);
        setDbURL(dbURL);
        setUserName(userName);
        setPassword(password);
    }

    public String getDriverName() {
        return driverName;
    }

    public final void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDbURL() {
        return dbURL;
    }

    public final void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public String getUserName() {
        return userName;
    }

    public final void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }
    
    
}
