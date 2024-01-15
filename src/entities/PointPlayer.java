/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import Model.PlayerVM;
import main.MyConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jondd
 */
public class  PointPlayer {
    public static int playerId;
    public static String playerName;
    public static String playerUserName;
    public static String playerEmail;
    public static int playerPoints = 0;
    public static int playerMaxPoints = 0;

     public PointPlayer()
    {
    }
    
    public static void setPlayerId(int playerIdSet) {
        playerId = playerIdSet;
    }
    
    public static List<PlayerVM> GetListPointPlayer()
    {
        List<PlayerVM> playerVMs = new ArrayList<>();
        MyConnection conn = new MyConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "Select a.PlayerId,a.PlayerName,a.PlayerUsername,a.PlayerEmail,b.pointValue from players a inner join userpoint b on a.playerId = b.playerId order by b.pointValue desc LIMIT 5";
        int playerIdDB = (int)getPlayerId();
        try {
            ps = conn.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next())
            {
                PlayerVM _playerVM = new PlayerVM();
                _playerVM.PlayerId = rs.getInt("PlayerId");
                _playerVM.PlayerName = rs.getString("PlayerName");
                _playerVM.PlayerUsername = rs.getString("playerUsername");
                _playerVM.PlayerEmail = rs.getString("PlayerEmail");
                _playerVM.PointValue = rs.getInt("pointValue");
                
                playerVMs.add(_playerVM);
            }
        } catch (Exception ex) {
            System.out.println("Error "+ex);
        }
         return playerVMs;
    }
    
    public static void GetInfoPlayerDB()
    {
        MyConnection conn = new MyConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "Select PlayerId,PlayerName,PlayerUsername,PlayerEmail from players where PlayerId = ?";
        int playerIdDB = (int)getPlayerId();
        try {
            ps = conn.getConnection().prepareStatement(query);
            ps.setInt(1, playerIdDB);
            rs = ps.executeQuery();
            if(rs.next())
            {
                playerName = rs.getString("PlayerName");
                playerUserName = rs.getString("PlayerUsername");
                playerEmail = rs.getString("PlayerEmail");
            }
            else{
                System.out.println("Error get infomation Player");
            }
        } catch (Exception ex) {
            System.out.println("Error "+ex);
        }
        
    }
    public static void PostPlayerMaxPoint()
    {
        MyConnection conn = new MyConnection();
        PreparedStatement ps;
        ResultSet rs;
        String queryInsert = "insert into userpoint(PointValue,PlayerId) values(?,?)";
        String queryUpdate = "update userpoint set PointValue = ? where PlayerId = ?";
        String querySelect = "Select * from userpoint where PlayerId = ?";
        int playerIdDB = (int)getPlayerId();
        try {
            ps = conn.getConnection().prepareStatement(querySelect);
            ps.setInt(1, playerIdDB);
            rs = ps.executeQuery();
            if(rs.next())
            {
                ps = conn.getConnection().prepareStatement(queryUpdate);
                ps.setInt(1, getPlayerPoint());
                ps.setInt(2, playerIdDB);
                ps.executeUpdate();
            }else
            {
                ps = conn.getConnection().prepareStatement(queryInsert);
                ps.setInt(1, getPlayerPoint());
                ps.setInt(2, playerIdDB);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Error "+ex);
        }
    }
     public static void GetDBPlayerMaxPoint()
    {
        MyConnection conn = new MyConnection();
        PreparedStatement ps;
        ResultSet rs;
        String querySelect = "Select * from userpoint where PlayerId = ?";
        int playerIdDB = (int)getPlayerId();
        try {
            ps = conn.getConnection().prepareStatement(querySelect);
            ps.setInt(1, playerIdDB);
            rs = ps.executeQuery();
            if(rs.next())
            {
              playerMaxPoints = rs.getInt("PointValue");
            }else
            {
               playerMaxPoints = 0;
            }
        } catch (Exception ex) {
            System.out.println("Error "+ex);
        }
    }
    public static int getPlayerId() {
        
        return playerId;
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static String getPlayerUserName() {
        return playerUserName;
    }

    public static String getPlayerEmail() {
        return playerEmail;
    }

    public static int getPlayerPoint() {
        return playerPoints;
    }

    public static void setPlayerPoint(int playerPoint) {
        playerPoints += playerPoint;
    }

    public static int getPlayerMaxPoint() {
        return playerMaxPoints;
    }
    
    public static void setPlayerMaxPoint(int playerMaxPoint) {
        playerMaxPoints = playerMaxPoint;
    }
    
    public static void ResetPoint()
    {
        playerPoints = 0;
    }
}
