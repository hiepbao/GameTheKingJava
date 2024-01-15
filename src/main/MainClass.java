package main;

import JFrameForm.LoginPlayer;

public class MainClass {

	public static void main(String[] args) {
            LoginPlayer login = new LoginPlayer();
            login.pack();
            login.setLocationRelativeTo(null);
            login.show();
//		new Game();
	}

}
