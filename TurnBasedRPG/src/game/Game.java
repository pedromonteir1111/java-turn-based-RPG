package game;

import java.awt.Color;

import combat.CombatSystem;
import entities.Player;
import entities.Warrior;
import gamestates.GameMenu;
import gamestates.Gamestate;
import userInputs.Mouse;

public class Game {
	
	private ScreenSettings screenSettings;
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Player player;
//	private InputsFromKeyboard inputsFromKeyboard = new InputsFromKeyboard(gamePanel, player);
	
	public Game() {
		
		screenSettings = new ScreenSettings(4);
		
		initObjects();
		
		gameWindow = new GameWindow(gamePanel, screenSettings);
		
		gamePanel.setBackground(Color.black);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		
	}
	
	public void initObjects() {
		
		player = new Warrior(screenSettings);
		gamePanel = new GamePanel(player, screenSettings);
			
	}
}