package game;

import java.awt.Color;

import playerClasses.Player;
import playerClasses.Warrior;

public class Game {
	
	private ScreenSettings screenSettings;
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Player player;
//	private InputsFromKeyboard inputsFromKeyboard = new InputsFromKeyboard(gamePanel, player);
	
	public Game() {
		
		screenSettings = new ScreenSettings(5);
		player = new Warrior();
		gamePanel = new GamePanel(player, screenSettings);
		gameWindow = new GameWindow(gamePanel, screenSettings);
		
		gamePanel.setBackground(Color.black);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		
		
		
	}
	
}