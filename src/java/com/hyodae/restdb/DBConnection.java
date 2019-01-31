/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hyodae.restdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hyodaekim
 */
public class DBConnection {
    
    public static Connection dbCon;
    public Statement dbStmt;
    public ResultSet dbRst;

    /* DB SetConnection */
    public static Connection setDBConnection() throws SQLException, ClassNotFoundException {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://emba.kr:3306/sample";

        Class.forName(driver);
        dbCon = DriverManager.getConnection(url, "root", "password_here");

        return dbCon;        
    }

    public ResultSet getResultSet(String sqlQuery, Connection conn) throws SQLException {

        dbCon = conn;
        
        try {
            dbStmt = dbCon.createStatement();
            dbRst = dbStmt.executeQuery(sqlQuery);
        } catch (SQLException se) {
            System.out.println(se);
        }
        
        return dbRst;
        
    }

    public String getCustomerInfo(ResultSet rs) throws SQLException {
        
        String result = 
                "<!DOCTYPE html PUBLIC>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Customer Infomation</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <table border=\"1\">\n" +
                "       <th>Customer ID</th>\n" +
                "       <th>Discount Code</th>\n" +
                "       <th>Zip</th>\n" +
                "       <th>Name</th>\n" +
                "       <th>Address1</th>\n" +
                "       <th>Address2</th>\n" +
                "       <th>City</th>\n" +
                "       <th>State</th>\n" +
                "       <th>Phone</th>\n" +
                "       <th>Fax</th>\n" +
                "       <th>Email</th>\n" +
                "       <th>Credit Limit</th>\n";
        
        while (rs.next()) {
            result = result
                + "<tr>"
                + "<td>" + rs.getInt("customer_id") + "</td>"
                + "<td>" + rs.getString("discount_code") + "</td>"
                + "<td>" + rs.getString("zip") + "</td>"
                + "<td>" + rs.getString("name") + "</td>"
                + "<td>" + rs.getString("addressline1") + "</td>"
                + "<td>" + rs.getString("addressline2") + "</td>"
                + "<td>" + rs.getString("city") + "</td>"
                + "<td>" + rs.getString("state") + "</td>"
                + "<td>" + rs.getString("phone") + "</td>"
                + "<td>" + rs.getString("fax") + "</td>"
                + "<td>" + rs.getString("email") + "</td>"
                + "<td>" + rs.getInt("credit_limit") + "</td>"
                + "</tr>";
        }

        result = result +
                "</table>\n" +
                "</body>\n" +
                "</html>";
        
        return result;
    }
}
