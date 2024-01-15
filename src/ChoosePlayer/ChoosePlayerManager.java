/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChoosePlayer;

import static ChoosePlayer.ChoosePlayer.getIndexChoose;
import static ChoosePlayer.ChoosePlayer.imgChooose;
import static ChoosePlayer.ChoosePlayer.setImg;
import utilz.LoadSave;

/**
 *
 * @author jondd
 */
public class ChoosePlayerManager {
    public static final ChoosePlayer current = new ChoosePlayer();
    
    private ChoosePlayerManager() {
        
    }
    public static ChoosePlayer getChoosePlayerManager()
    {
        return current;
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
        }
    }
    
}
