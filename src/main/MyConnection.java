/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.sql.*;

/**
 *
 * @author jondd
 */
public class MyConnection {
    public static Connection getConnection()
    {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hkgame","root","root");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
