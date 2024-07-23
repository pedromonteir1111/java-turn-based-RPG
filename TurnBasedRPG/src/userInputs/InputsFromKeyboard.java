package userInputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import combat.CombatStates;
import combat.CombatSystem;
import game.GamePanel;
import game.ScreenSettings;
//import game.MapObjects;
import gamestates.Gamestate;
import map.MapGenerator;
import ui.InventoryUI;
import entities.Player;


public class InputsFromKeyboard implements KeyListener {
	
	private ScreenSettings screenSettings;
	private MapGenerator map;
	private GamePanel gamePanel;
	private Player playerClass;
	private InventoryUI inventoryUI;
	private CombatSystem combat;
	private int spriteIndex = 0;
	private int spriteCounter = 0;
	
	
	public InputsFromKeyboard(GamePanel gamePanel, Player playerClass, CombatSystem combat, InventoryUI inventoryUI, ScreenSettings screenSettings, MapGenerator mapGenerator) {
		
		this.gamePanel = gamePanel;
		this.playerClass = playerClass;
		this.combat = combat;
		this.inventoryUI = inventoryUI;
		this.screenSettings = screenSettings;
		this.map = mapGenerator;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		updatePlayerAnimation();
		
		if (e.getKeyCode() == KeyEvent.VK_I) {
			
			gamePanel.toggleInventory();
			return;
		}
		
		if (gamePanel.isInventoryOpen()) {
			return;
		}
			
		switch(e.getKeyCode()) {	
		
		case KeyEvent.VK_W:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				
				playerClass.changeDeltaY(-playerClass.getSpeed());
				playerClass.setCurrentPlayerPosition(playerClass.getUpDirection()[spriteIndex + 1]);	
				gamePanel.repaint();
				gamePanel.revalidate();
				
			}
			
			break;
		
		case KeyEvent.VK_A:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				playerClass.changeDeltaX(-playerClass.getSpeed());
				
				playerClass.setCurrentPlayerPosition(playerClass.getLeftDirection()[spriteIndex + 1]);
					
				gamePanel.repaint();
				gamePanel.revalidate();		
				
			}
			
			break;
			
		case KeyEvent.VK_S:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				playerClass.changeDeltaY(playerClass.getSpeed());
				
				playerClass.setCurrentPlayerPosition(playerClass.getDownDirection()[spriteIndex + 1]);
				gamePanel.repaint();
				gamePanel.revalidate();	
							
			}
			
			break;	
			
		case KeyEvent.VK_D:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				playerClass.changeDeltaX(playerClass.getSpeed());
				
				playerClass.setCurrentPlayerPosition(playerClass.getRightDirection()[spriteIndex + 1]);
				
				if(playerClass.getX() > screenSettings.getScreenWidth() - screenSettings.getTileSize() && map.getIndexX() + 1 <= map.getMaxIndexX()) {
					
					playerClass.setX(0);
					map.loadNewRoom(1, 0);
					
				}
				
				gamePanel.repaint();
				gamePanel.revalidate();
					
			}
			
			break;
			
			
		case KeyEvent.VK_O:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				
				combat.startCombat(new int[] { 0, 0 });
				
				gamePanel.repaint();
				gamePanel.revalidate();
				
				System.out.println(combat.getCombatLoss());
				System.out.println(combat.getCombatWin());
			}
			
			break;
			
		case KeyEvent.VK_P:
			
			combat.leaveCombat();
			gamePanel.repaint();
			gamePanel.revalidate();
			break;
			
		case KeyEvent.VK_SPACE:
			
			if (Gamestate.state == Gamestate.COMBAT && CombatStates.state != CombatStates.ENEMY_TURN) {
				
				Inputs.lastInput = Inputs.SPACE;
				combat.runCombat(-1, -1, Inputs.SPACE);	
				gamePanel.repaint();
				gamePanel.revalidate();
				
			}
			
			break;
			
		case KeyEvent.VK_Q:
			
			if (Gamestate.state == Gamestate.COMBAT) {
				
				Inputs.lastInput = Inputs.Q;
				combat.runCombat(-1, -1, Inputs.Q);	
				gamePanel.repaint();
				gamePanel.revalidate();
				
			}
			
			break;
			
		case KeyEvent.VK_ESCAPE:
			
			if (Gamestate.state == Gamestate.COMBAT && CombatStates.state == CombatStates.SELECT_ATTACK_LOCATION) {
				
				Inputs.lastInput = Inputs.ESC;
				combat.runCombat(-1, -1, Inputs.ESC);	
				gamePanel.repaint();
				gamePanel.revalidate();
				
			}
			
			break;
		
		}
			
	}

	private void updatePlayerAnimation() {
		
		spriteCounter++;
		
		if (spriteCounter > 10) {
			
			spriteIndex = (spriteIndex + 1) % 2;
			spriteCounter = 0;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (gamePanel.isInventoryOpen()) {
			return;
		}
		
		if (Gamestate.state != Gamestate.COMBAT) {
		  switch(e.getKeyCode()) {
		  
		  case KeyEvent.VK_W:
		  
		  playerClass.setCurrentPlayerPosition(playerClass.getUpDirection()[0]);
		  gamePanel.repaint();
		  
		  break;
		  
		  case KeyEvent.VK_S:
		  
		  playerClass.setCurrentPlayerPosition(playerClass.getDownDirection()[0]);
		  gamePanel.repaint();
		  
		  break;
		  
		  case KeyEvent.VK_A:
		  
		  playerClass.setCurrentPlayerPosition(playerClass.getLeftDirection()[0]);
		  gamePanel.repaint();
		  
		  break;
		  
		  case KeyEvent.VK_D:
		  
		  playerClass.setCurrentPlayerPosition(playerClass.getRightDirection()[0]);
		  gamePanel.repaint();
		  
		  break;
		  
		  }
		  
		}
	}

}