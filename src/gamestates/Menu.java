package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import ui.SkinButton;
import ui.UrmButton;
import static utilz.Constants.UI.URMButtons.URM_SIZE;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {

	private MenuButton[] buttons = new MenuButton[4];
	private BufferedImage backgroundImg, backgroundImgPink;
	private int menuX, menuY, menuWidth, menuHeight;
        private String namePlayer;
        private Font font;
        private SkinButton btnChangeSkin;
        
	public Menu(Game game) {
		super(game);
		loadButtons();
                loadBtnChangeSkin();
		loadBackground();
                loadNamePlayer(game.getPlayerName());
		backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);

	}

        private void loadNamePlayer(String username)
        {
            font = new Font("Arial", Font.ITALIC , 26);
            namePlayer =  "Hello! "+username;
        }
        
	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
		menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
		menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
		menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
		menuY = (int) (25 * Game.SCALE);
	}

        private void loadBtnChangeSkin()
        {
                btnChangeSkin = new SkinButton(150, (int) (100 * Game.SCALE), 0, Gamestate.SKIN);
        }
	private void loadButtons() {
		buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (130 * Game.SCALE), 0, Gamestate.PLAYING);
		buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (200 * Game.SCALE), 1, Gamestate.OPTIONS);
		buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (270 * Game.SCALE), 3, Gamestate.CREDITS);
		buttons[3] = new MenuButton(Game.GAME_WIDTH / 2, (int) (340 * Game.SCALE), 2, Gamestate.QUIT);
                
               
                
	}

	@Override
	public void update() {
            btnChangeSkin.update();

            for (MenuButton mb : buttons)
			mb.update();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
                g.setFont(font); // Set font
                 g.setColor(Color.WHITE);
                g.drawString(namePlayer,50, 50);

               
                g.setFont(new Font("Arial", Font.BOLD , 26));
                g.drawString("Welcome to The King ",50, 100);
                
               
                
		for (MenuButton mb : buttons)
                {
                    mb.draw(g);
                }
                 btnChangeSkin.draw(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {
            //change skin
            if (isIn(e, btnChangeSkin)) {
			btnChangeSkin.setMousePressed(true);
		}
            //change skin
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMousePressed(true);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//change skin
                if (isIn(e, btnChangeSkin)) {
			if (btnChangeSkin.isMousePressed())
                        {
				Gamestate.state = Gamestate.SKIN;
                        }
                }
                btnChangeSkin.resetBools();
                //change skin
            for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed())
					mb.applyGamestate();
				if (mb.getState() == Gamestate.PLAYING)
					game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex());
				break;
			}
		}
		resetButtons();
	}

	private void resetButtons() {
		for (MenuButton mb : buttons)
			mb.resetBools();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
             //change skin   
                btnChangeSkin.setMouseOver(false);
		if (isIn(e, btnChangeSkin))
                {
                    btnChangeSkin.setMouseOver(true);
                }
            //change skin
            
		for (MenuButton mb : buttons)
			mb.setMouseOver(false);

		for (MenuButton mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}