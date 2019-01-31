/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hyodae.restdb;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * REST Web Service
 *
 * @author hyodaekim
 */
@Path("customer")
public class REST_Endpoint {

    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of REST_Endpoint
     */
    public REST_Endpoint() {
    }

    /**
     * Retrieves representation of an instance of com.hyodae.REST_Endpoint
     * @return an instance of java.lang.String
     */
    @GET
//    @Produces(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_HTML)
    public String getText() {
        
        DBConnection dbCon = new DBConnection();
        Connection conn = null;
        ResultSet rs= null;

        String sql = "select * from customer";
        String resultPage = "Invalid Page";

        try {
            conn = DBConnection.setDBConnection();
            rs = dbCon.getResultSet(sql, conn);
            resultPage = dbCon.getCustomerInfo(rs);
        }
        catch(ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                if (conn!=null) conn.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }        
        
        return resultPage;
       
/*        
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://emba.kr:3306/sample";
        String sql = "select * from customer";
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String result = "";
 
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, "root", "wjfjs202");
            System.out.println("Success to connect DB");

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            result = 
                    "<!DOCTYPE html PUBLIC>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Table</title>\n" +
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
        }
        catch(Exception e) {
                System.out.println("Failed to connect DB");
        }
        finally {
                try {
                        if (con!=null) con.close();
                }
                catch (Exception e) {
                        System.out.println(e.getMessage());
                }
        }        
        
        return result;
*/        
    }

    /**
     * PUT method for updating or creating an instance of REST_Endpoint
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
}
