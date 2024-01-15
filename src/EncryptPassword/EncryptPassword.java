/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EncryptPassword;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import main.MyConnection;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author jondd
 */
public class EncryptPassword {

    public EncryptPassword() {
    }
    
    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convert byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                String hex = Integer.toHexString(0xff & hashedByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null;
        }
    }
    
    public static  boolean isValidCredentials(String username, String encryptedPassword) {
        
        MyConnection conn = new MyConnection();
        PreparedStatement ps;
        ResultSet rs;
        boolean isValid = false;
        String query = "select * from players where PlayerUsername like ? and PlayerPassword like ?";
        try {
            ps = conn.getConnection().prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2, encryptedPassword);
            rs = ps.executeQuery();
            if(rs.next())
            {
                isValid = true;
            }else
            {
                isValid = false;
            }
        } catch (Exception e) {
            System.out.println("Err: "+e);
        }
       return isValid;
    }
}
