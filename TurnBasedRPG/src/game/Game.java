package game;

import java.awt.Color;

import playerClasses.Player;
import playerClasses.Warrior;

public class Game {
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Player player;
//	private InputsFromKeyboard inputsFromKeyboard = new InputsFromKeyboard(gamePanel, player);
	
	public Game() {
		
		player = new Warrior();
		gamePanel = new GamePanel(player);
		gameWindow = new GameWindow(gamePanel);
		
		gamePanel.setBackground(Color.black);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		
		
		
	}
	
}