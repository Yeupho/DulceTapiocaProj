package com.company;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DatabasePull {

    public static String getTransID(String s, String SQL) throws SQLException {

        Connection c;
        c = DBconnect.connect();

        // ResultSet
        ResultSet rs = c.createStatement().executeQuery(SQL);

        while (rs.next()) {
            s = rs.getString(1);
        }

        System.out.println("Last Entry For Transaction ID: "+ s);
        c.close();

        return s;

    }

}

