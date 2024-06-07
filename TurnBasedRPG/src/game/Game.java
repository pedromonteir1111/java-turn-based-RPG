package game;

import java.awt.Color;

import combat.BattleGrid;
import playerClasses.Player;
import playerClasses.Warrior;

public class Game {
	
	private ScreenSettings screenSettings;
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Player player;
	private BattleGrid battleGrid;
//	private InputsFromKeyboard inputsFromKeyboard = new InputsFromKeyboard(gamePanel, player);
	
	public Game() {
		
		screenSettings = new ScreenSettings(4);
		player = new Warrior();
		battleGrid = new BattleGrid(screenSettings);
		gamePanel = new GamePanel(player, screenSettings, battleGrid);
		gameWindow = new GameWindow(gamePanel, screenSettings);
		
		gamePanel.setBackground(Color.black);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		
		
		
	}
	
}