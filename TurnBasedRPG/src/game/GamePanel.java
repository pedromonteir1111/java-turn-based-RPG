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
import inventory.PlayerInventory;
import map.MapGenerator;
import userInputs.InputsFromKeyboard;
import userInputs.Mouse;

public class GamePanel extends JPanel {
	
	private Player playerClass;
	private Player mage;
	private ScreenSettings screenSettings;
	private CombatSystem combat;
	private Mouse mouse;
	
	private InputsFromKeyboard inputsFromKeyboard;
	
	private MapGenerator mapGenerator;
	
<<<<<<< HEAD
	
//	private InventoryUI inventoryUI;
	private boolean isInventoryOpen;
	

	public GamePanel(Player playerClass, ScreenSettings screenSettings, PlayerInventory playerInventory) {
		
		this.playerClass = playerClass;
		
=======
	public GamePanel(Player playerClass, Player mage, ScreenSettings screenSettings) {
		
		this.playerClass = playerClass;
		this.mage = mage;
>>>>>>> db778711b96a875628cb3f952ebe1755142726c3
		this.screenSettings = screenSettings; 
		
		/* this.inventoryUI = new InventoryUI(playerInventory, playerClass); */
		this.isInventoryOpen = false;
			
		mapGenerator = new MapGenerator(this, screenSettings);
<<<<<<< HEAD
		
		combat = new CombatSystem(playerClass, screenSettings, this);
		
=======
		combat = new CombatSystem(playerClass, mage, screenSettings, this);
>>>>>>> db778711b96a875628cb3f952ebe1755142726c3
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
				combat.drawEntities(g2D);
				break;
				
			default:
				break;
		}
<<<<<<< HEAD
		
		playerClass.draw(g2D);
		
=======
		if (Gamestate.state != Gamestate.COMBAT) {
			playerClass.draw(g2D);
		}
>>>>>>> db778711b96a875628cb3f952ebe1755142726c3
		g2D.dispose();
	}
		
	//		System.out.println("Seu inventário: ");
	//		
	//		for (Item item : playerInventory.getItems()) {
	//			System.out.println(item.getItemName() + " ~~~ Level: " + item.getItemLevel());
	//		}
//			boolean isVisible = inventoryUI.isVisible();
//			
//			inventoryUI.setVisible(false);
//			
//			if (inventoryUI.isVisible()) {
//				Gamestate.state = Gamestate.INVENTORY;
//			}
//			
//			else {
//				Gamestate.state = Gamestate.PLAYING;
//			}
//				
//		}
}
	
	