package gamestates;

import Model.PlayerVM;
import entities.PointPlayer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.Game;
import utilz.LoadSave;

public class Credits extends State implements Statemethods {
	private BufferedImage backgroundImg, creditsImg;
	private int bgX, bgY, bgW, bgH;
	private float bgYFloat;
        private int indexTop = 1;
        private Random random;
        
        public List<PlayerVM> listNew;

        public int yListPoint = 200;

	private ArrayList<ShowEntity> entitiesList;

	public Credits(Game game) {
		super(game);
                random = new Random();
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
		creditsImg = LoadSave.GetSpriteAtlas(LoadSave.CREDITS);
		bgW = (int) (creditsImg.getWidth() * Game.SCALE);
		bgH = (int) (creditsImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = Game.GAME_HEIGHT;
		loadEntities();
                
                listNew = new ArrayList<PlayerVM>();
                List<PlayerVM> list = PointPlayer.GetListPointPlayer();
                for(PlayerVM item : list)
                {
                       PlayerVM _playerVM = new PlayerVM();
                      _playerVM.PlayerId = item.PlayerId;
                      _playerVM.PlayerName = item.PlayerName;
                      _playerVM.PlayerEmail = item.PlayerName;
                      _playerVM.PlayerUsername = item.PlayerUsername;
                      _playerVM.PointValue = item.PointValue;
                      _playerVM.yListPoint = yListPoint;
                      yListPoint =  _playerVM.yListPoint + 50;
                      listNew.add(_playerVM);
                }
	}

	private void loadEntities() {
		entitiesList = new ArrayList<>();
		entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS), 8, 64, 40), (int) (Game.GAME_WIDTH * 0.05), (int) (Game.GAME_HEIGHT * 0.75)));
		entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE), 4, 72, 32), (int) (Game.GAME_WIDTH * 0.15), (int) (Game.GAME_HEIGHT * 0.65)));
		entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.PINKSTAR_ATLAS), 8, 34, 30), (int) (Game.GAME_WIDTH * 0.7), (int) (Game.GAME_HEIGHT * 0.65)));
		entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.SHARK_ATLAS), 4, 34, 30), (int) (Game.GAME_WIDTH * 0.8), (int) (Game.GAME_HEIGHT * 0.75)));
	}

	private BufferedImage[] getIdleAni(BufferedImage atlas, int spritesAmount, int width, int height) {
		BufferedImage[] arr = new BufferedImage[spritesAmount];
		for (int i = 0; i < spritesAmount; i++)
			arr[i] = atlas.getSubimage(width * i, 0, width, height);
		return arr;
	}
        private Color getRndColor()
        {
            int r = random.nextInt(255);
            int b = random.nextInt(255);
            int g = random.nextInt(255);
            return new Color(r,b,g);
        }

	@Override
	public void update() {
		//bgYFloat -= 0.2f;
		for (ShowEntity se : entitiesList)
			se.update();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
                //g.drawImage(creditsImg, bgX, (int) (bgY + bgYFloat), bgW, bgH, null);
                g.setFont(new Font("Arial", Font.BOLD , 26));
                g.setColor(getRndColor());
                g.drawString("LIST TOP RANK",Game.GAME_WIDTH / 2 - 50, 100);
                g.setColor(Color.WHITE);
                for(PlayerVM player : listNew)
                {
                       g.drawString("Player's ID: " + player.getPlayerId() + "  |  Name: " + player.getPlayerName() + "  |  Username: " + player.getPlayerUsername() + "  |  PointValue: " + player.getPointValue(),150, player.getyListPoint());
                }
                
		for (ShowEntity se : entitiesList)
			se.draw(g);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			bgYFloat = 0;
			setGamestate(Gamestate.MENU);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
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
