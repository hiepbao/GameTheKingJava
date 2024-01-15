/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author jondd
 */
public class PlayerVM {

    public int PlayerId;
    public String PlayerName;
    public String PlayerEmail;
    public String PlayerUsername;
    public int PointValue;
     public int yListPoint;

    public int getyListPoint() {
        return yListPoint;
    }

    public void setyListPoint(int yListPoint) {
        this.yListPoint = yListPoint;
    }
   
    
    public int getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(int PlayerId) {
        this.PlayerId = PlayerId;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String PlayerName) {
        this.PlayerName = PlayerName;
    }

    public String getPlayerEmail() {
        return PlayerEmail;
    }

    public void setPlayerEmail(String PlayerEmail) {
        this.PlayerEmail = PlayerEmail;
    }

    public String getPlayerUsername() {
        return PlayerUsername;
    }

    public void setPlayerUsername(String PlayerUsername) {
        this.PlayerUsername = PlayerUsername;
    }

    public int getPointValue() {
        return PointValue;
    }

    public void setPointValue(int PointValue) {
        this.PointValue = PointValue;
    }
    
    
}
