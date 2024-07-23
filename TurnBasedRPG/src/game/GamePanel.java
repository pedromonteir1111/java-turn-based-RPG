package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import combat.CombatSystem;
import combat.HUD;
import entities.OldMan;
import entities.Player;
import gamestates.Gamestate;
import inventory.PlayerInventory;
import map.MapGenerator;
import ui.InventoryUI;
import userInputs.InputsFromKeyboard;
import userInputs.Mouse;

public class GamePanel extends JPanel {
	
	private Player playerClass;
	private CombatSystem combat;
	private Mouse mouse;
	private HUD hud;
	
	private InputsFromKeyboard inputsFromKeyboard;
	
	private MapGenerator mapGenerator;
	
	private InventoryUI inventoryUI;
	private OldMan oldMan;
	private PlayerInventory playerInventory;
	

	public GamePanel(Player playerClass, Player mage, Player rogue, ScreenSettings screenSettings, PlayerInventory playerInventory, OldMan oldMan) {
		
		this.playerClass = playerClass; 
		
		inventoryUI = new InventoryUI(playerInventory, screenSettings.getScreenWidth(), screenSettings.getScreenHeight());
		
		hud = new HUD(screenSettings);
		combat = new CombatSystem(playerClass, mage, rogue, screenSettings, this, playerInventory, hud);
		mapGenerator = new MapGenerator(this, screenSettings, combat);
		this.oldMan = oldMan;
		this.playerInventory = playerInventory;
		inputsFromKeyboard = new InputsFromKeyboard(this, playerClass, combat, inventoryUI, screenSettings, mapGenerator, oldMan);
		mouse = new Mouse(combat, inventoryUI);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		this.addKeyListener(inputsFromKeyboard);
	
	}
	
	public void toggleInventory() {
		
		inventoryUI.toggleInventoryVisibility();
		repaint();
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
				combat.drawEntities(g2D);
				hud.draw(g2D);
				break;
				
			default:
				break;
		}
		
		if(mapGenerator.getIndexX() == 0 && mapGenerator.getIndexY() == 2) {
			oldMan.draw(g2D, playerInventory.getGold());
		}
		
		if (Gamestate.state != Gamestate.COMBAT) {
			playerClass.draw(g2D);
		}
		
		inventoryUI.draw(g2D);
		
		g2D.dispose();
	}
	
	
	
	
}
	
	