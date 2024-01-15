/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamestates;

import ChoosePlayer.ChoosePlayer;
import static ChoosePlayer.ChoosePlayer.getIndexChoose;
import static ChoosePlayer.ChoosePlayer.imgChooose;
import ChoosePlayer.ChoosePlayerManager;
import Model.PlayerVM;
import entities.Player;
import entities.PointPlayer;
import ui.MenuButton;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import main.Game;
import ui.NextSkinButton;
import ui.SkinButton;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

/**
 *
 * @author jondd
 */
public class Skins extends State implements Statemethods{
    
    private BufferedImage backgroundImg, backgroundImgPink;
    private int menuX, menuY, menuWidth, menuHeight;
    private NextSkinButton menu, next;
    private ArrayList<Skins.ShowEntity> entitiesList;
    private int countChangeSkin = 0;
    
    private SkinButton btnChangeSkin;
    private int indexChoosePlayer;
    private ChoosePlayer choosePlayer;
    private PointPlayer pointPlayer;

        
    public Skins(Game game) {
        super(game);
        loadBackground();
        initButtons();
        loadBtnChangeSkin();
        loadPlayer();
        choosePlayer.getImgSpriteAtlas();
        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
        
    }
    public void loadPlayer() {
        entitiesList = new ArrayList<>();
        entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS), 8, 64, 40), Game.GAME_WIDTH / 2 - 120, (int) (170 * Game.SCALE)));
	entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.PLAYER3_ATLAS), 8, 64, 40), Game.GAME_WIDTH / 2 - 120, (int) (170 * Game.SCALE)));
        entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.PLAYER1_ATLAS), 8, 64, 40), Game.GAME_WIDTH / 2 - 120, (int) (170 * Game.SCALE)));
    }
    
    private BufferedImage[] getIdleAni(BufferedImage atlas, int spritesAmount, int width, int height) {
		BufferedImage[] arr = new BufferedImage[spritesAmount];
		for (int i = 0; i < spritesAmount; i++)
			arr[i] = atlas.getSubimage(width * i, 0, width, height);
		return arr;
	}
    
    private void loadBtnChangeSkin()
        {
                btnChangeSkin = new SkinButton(Game.GAME_WIDTH / 2, (int) (340 * Game.SCALE), 1, Gamestate.SKIN);
        }
    
    private void initButtons() {
		int menuX = (int) (220 * Game.SCALE);
		int nextX = (int) (555 * Game.SCALE);
		int y = (int) (195 * Game.SCALE);
		next = new NextSkinButton(nextX, y, URM_SIZE, URM_SIZE, 0);
		menu = new NextSkinButton(menuX, y, URM_SIZE, URM_SIZE, 1);
	}

    private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.SKIN_BACKGROUND);
		menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
		menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
		menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
		menuY = (int) (25 * Game.SCALE);
    }
    
    @Override
    public void update() {
        next.update();
        menu.update();
        btnChangeSkin.update();
        
        for (ShowEntity se : entitiesList)
			se.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
        next.draw(g);
        menu.draw(g);
        btnChangeSkin.draw(g);
        
        entitiesList.get(countChangeSkin).draw(g);
//        for (ShowEntity se : entitiesList)
//			se.draw(g);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(menu, e))
        {
            if(countChangeSkin > 0)
            {
                --countChangeSkin;
                indexChoosePlayer = countChangeSkin;
                System.out.println(indexChoosePlayer);
            }else
            {
                countChangeSkin = entitiesList.size()-1;
                indexChoosePlayer = countChangeSkin;
                System.out.println(indexChoosePlayer);
            }
            menu.setMousePressed(true);
        }
        else if (isIn(next, e))
        {
            if(countChangeSkin < entitiesList.size()-1)
            {
                ++countChangeSkin;
                indexChoosePlayer = countChangeSkin;
                System.out.println(indexChoosePlayer);
            }else
            {
                countChangeSkin = 0;
                indexChoosePlayer = 0;
                System.out.println(indexChoosePlayer);
            }
            next.setMousePressed(true);
        }
        //change skin
            if (isIn(e, btnChangeSkin)) {
                    pointPlayer.GetDBPlayerMaxPoint();
                    if(pointPlayer.getPlayerMaxPoint() < 100 && indexChoosePlayer > 1)
                    {
                            choosePlayer.setIndexChoose(indexChoosePlayer);
                            JOptionPane.showMessageDialog(game.getGamePanel(), "Not enough 200 point");
                            choosePlayer.getImgSpriteAtlas();
                            game.getPlaying().getPlayer().loadAnimations();
                    }else
                    {
                        choosePlayer.setIndexChoose(indexChoosePlayer);
                        choosePlayer.getImgSpriteAtlas();
                        game.getPlaying().getPlayer().loadAnimations();
                        Gamestate.state = Gamestate.MENU;
                    }
                        

                        
			btnChangeSkin.setMousePressed(true);
                        
		}
        //change skin
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(menu, e)) {
            if (menu.isMousePressed()) {
            }
	} else if (isIn(next, e))
            if (next.isMousePressed()) {
            }
		menu.resetBools();
		next.resetBools();
                
        //change skin
                if (isIn(e, btnChangeSkin)) {
			if (btnChangeSkin.isMousePressed())
                        {
				Gamestate.state = Gamestate.SKIN;
                        }
                }
                btnChangeSkin.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        next.setMouseOver(false);
	menu.setMouseOver(false);
		if (isIn(menu, e))
                {
                    menu.setMouseOver(true);
                }
		else if (isIn(next, e))
                {
                    next.setMouseOver(true);
                }
                
                //change skin   
                btnChangeSkin.setMouseOver(false);
		if (isIn(e, btnChangeSkin))
                {
                    btnChangeSkin.setMouseOver(true);
                }
            //change skin
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			Gamestate.state = Gamestate.MENU;
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
    private boolean isIn(NextSkinButton b, MouseEvent e) {
		return b.getBounds().contains(e.getX(), e.getY());
    }
    
    private class ShowEntity {
		private BufferedImage[] idleAnimation;
		private int x, y, aniIndex, aniTick;

		public ShowEntity(BufferedImage[] idleAnimation, int x, int y) {
			this.idleAnimation = idleAnimation;
			this.x = x;
			this.y = y;
		}

		public void draw(Graphics g) {
			g.drawImage(idleAnimation[aniIndex], x, y, (int) (idleAnimation[aniIndex].getWidth() * 4), (int) (idleAnimation[aniIndex].getHeight() * 4), null);
		}

		public void update() {
			aniTick++;
			if (aniTick >= 25) {
				aniTick = 0;
				aniIndex++;
				if (aniIndex >= idleAnimation.length)
					aniIndex = 0;
			}

		}
	}
}
