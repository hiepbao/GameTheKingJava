/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChoosePlayer;

import java.awt.image.BufferedImage;
import utilz.LoadSave;

/**
 *
 * @author jondd
 */
public class ChoosePlayer {

    public static int indexChoose;
    public static BufferedImage imgChooose;
    public static BufferedImage img;
    
    public ChoosePlayer(){
        
    }
    
    public static BufferedImage getImg() {
        System.out.println("img"+img);
        return img;
    }

    public static void setImg(BufferedImage img) {
        ChoosePlayer.img = img;
    }
    
    public static int getIndexChoose() {
         System.out.println("img"+indexChoose);
        return indexChoose;
    }

    public static void setIndexChoose(int indexChoose) {
        ChoosePlayer.indexChoose = indexChoose;
    }
    
    public static void getImgSpriteAtlas()
    {
        if(getIndexChoose() == 0)
        {
            imgChooose = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
            setImg(imgChooose);
        }else if(getIndexChoose() == 1)
        {
            imgChooose = LoadSave.GetSpriteAtlas(LoadSave.PLAYER3_ATLAS);
            setImg(imgChooose);
            System.out.println("img chooose"+ imgChooose);
        }
        else if(getIndexChoose() == 2)
        {
            imgChooose = LoadSave.GetSpriteAtlas(LoadSave.PLAYER1_ATLAS);
            setImg(imgChooose);
            System.out.println("img chooose"+ imgChooose);
        }
    }
    
    public static void loadChooseSkin()
    {
       getImg();
    }
}
