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
