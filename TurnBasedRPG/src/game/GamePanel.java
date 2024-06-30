package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import combat.CombatSystem;
import entities.Player;
import gamestates.Gamestate;
import map.MapGenerator;
import userInputs.InputsFromKeyboard;
import userInputs.Mouse;

public class GamePanel extends JPanel {
	
	private Player playerClass;
	private ScreenSettings screenSettings;
	private CombatSystem combat;
	private Mouse mouse;
	
	private InputsFromKeyboard inputsFromKeyboard;
	
	private MapGenerator mapGenerator;
	
	public GamePanel(Player playerClass, ScreenSettings screenSettings) {
		
		this.playerClass = playerClass;
		this.screenSettings = screenSettings; 
		
		mapGenerator = new MapGenerator(this, screenSettings);
		combat = new CombatSystem(playerClass, screenSettings, this);
		inputsFromKeyboard = new InputsFromKeyboard(this, playerClass, combat);
		mouse = new Mouse(combat);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		this.addKeyListener(inputsFromKeyboard);
	
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g; // convertendo os gráficos p/ 2D propriamente
		
		mapGenerator.draw(g2D);
		
		switch (Gamestate.state) {
			case MENU:
				break;
	
			case COMBAT:
				combat.drawGrid(g2D);
				break;
				
			default:
				break;
		}
		playerClass.draw(g2D);
		
		g2D.dispose();
	}
	
	
	
	
}
	
	