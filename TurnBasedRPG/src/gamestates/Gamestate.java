package gamestates;

import java.awt.Color;


public enum Gamestate {
	
	MENU, PLAYING, COMBAT;
	
	public static Gamestate state = MENU; // garantir que o jogo é iniciado pelo menu

}
