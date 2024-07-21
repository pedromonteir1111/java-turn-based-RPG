package gamestates;

public enum Gamestate {
	
	MENU, PLAYING, COMBAT, INVENTORY;
	
	public static Gamestate state = MENU; // garantir que o jogo é iniciado pelo menu

}
