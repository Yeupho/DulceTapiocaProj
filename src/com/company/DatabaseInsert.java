package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 4/20/2016.
 */
public class DatabaseInsert {

    public static void insertThis(String SQL) throws SQLException {

        Connection c;
        c = DBconnect.connect();

        // ResultSet
        try {
            c.createStatement().executeUpdate(SQL);

            System.out.println("Insertion success!");
        } catch (Exception e) {
            System.out.println("Insertion failed");
            System.err.println(e.getMessage());
        }

        c.close();

    }
}

