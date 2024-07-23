package userInputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import combat.CombatStates;
import combat.CombatSystem;
import game.GamePanel;
import game.ScreenSettings;
//import game.MapObjects;
import gamestates.Gamestate;
import inventory.PlayerInventory;
import map.MapGenerator;
import ui.InventoryUI;
import entities.OldMan;
import entities.Player;


public class InputsFromKeyboard implements KeyListener {
	
	private ScreenSettings screenSettings;
	private MapGenerator map;
	private GamePanel gamePanel;
	private Player playerClass;
	private InventoryUI inventoryUI;
	private CombatSystem combat;
	private OldMan oldMan;
	private int spriteIndex = 0;
	private int spriteCounter = 0;
	
	private int upperBound, lowerBound, rightBound, leftBound, tileSize, scaledPixel;
	
	
	public InputsFromKeyboard(GamePanel gamePanel, Player playerClass, CombatSystem combat, InventoryUI inventoryUI, ScreenSettings screenSettings, MapGenerator mapGenerator, OldMan oldMan) {
		
		this.gamePanel = gamePanel;
		this.playerClass = playerClass;
		this.combat = combat;
		this.inventoryUI = inventoryUI;
		this.screenSettings = screenSettings;
		this.map = mapGenerator;
		this.oldMan = oldMan;
		
		this.scaledPixel = screenSettings.getScale();
		this.tileSize = screenSettings.getTileSize();
		this.rightBound = screenSettings.getScreenWidth() - tileSize * 3 + 2*screenSettings.getScale();
		this.leftBound = tileSize * 2 - 2 * screenSettings.getScale();
		this.lowerBound =  screenSettings.getScreenHeight() - tileSize * 4 - 4 * screenSettings.getScale();
		this.upperBound = 2*screenSettings.getScale();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		updatePlayerAnimation();
			
		switch(e.getKeyCode()) {	
		
		case KeyEvent.VK_W:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				
				playerClass.changeDeltaY(-playerClass.getSpeed());
				playerClass.setCurrentPlayerPosition(playerClass.getUpDirection()[spriteIndex + 1]);	
				
				if(playerClass.getY() < upperBound) {
					
					if (map.getCurrentRoom().hasTopDoor() &&
						playerClass.getX() >= tileSize * 9 - 3 * scaledPixel && playerClass.getX() < tileSize * 10 + 3 * scaledPixel
						&& map.getIndexY() - 1 >= 0) {
						
						playerClass.setY(lowerBound);
						map.loadNewRoom(0, -1);
					} else {
						playerClass.setY(upperBound);
					}
					
				}
				
				
				gamePanel.repaint();
				gamePanel.revalidate();
				
			}
			
			break;
		
		case KeyEvent.VK_A:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				playerClass.changeDeltaX(-playerClass.getSpeed());
				
				playerClass.setCurrentPlayerPosition(playerClass.getLeftDirection()[spriteIndex + 1]);
					
				if(playerClass.getX() < leftBound) {
					
					if (map.getCurrentRoom().hasLeftDoor() &&
						playerClass.getY() >= tileSize * 4 - 3 * scaledPixel && playerClass.getY() < tileSize * 4 + 3 * scaledPixel
						&& map.getIndexX() - 1 >= 0) {
						
						playerClass.setX(rightBound);
						map.loadNewRoom(-1, 0);
					} else {
						playerClass.setX(leftBound);
					}
					
				}
				
				gamePanel.repaint();
				gamePanel.revalidate();		
				
			}
			
			break;
			
		case KeyEvent.VK_S:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				playerClass.changeDeltaY(playerClass.getSpeed());
				
				playerClass.setCurrentPlayerPosition(playerClass.getDownDirection()[spriteIndex + 1]);
				
				if(playerClass.getY() > lowerBound) {
					
					if (map.getCurrentRoom().hasBottomDoor() &&
						playerClass.getX() >= tileSize * 9 - 3 * scaledPixel && playerClass.getX() < tileSize * 10 + 3 * scaledPixel
						&& map.getIndexY() + 1 <= map.getMaxIndexY()) {
						
						playerClass.setY(0);
						map.loadNewRoom(0, 1);
					} else {
						playerClass.setY(lowerBound);
					}
					
				}
				
				gamePanel.repaint();
				gamePanel.revalidate();	
							
			}
			
			break;	
			
		case KeyEvent.VK_D:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				playerClass.changeDeltaX(playerClass.getSpeed());
				
				playerClass.setCurrentPlayerPosition(playerClass.getRightDirection()[spriteIndex + 1]);
				
				if(playerClass.getX() > rightBound) {
					
					if (map.getCurrentRoom().hasRightDoor() &&
						playerClass.getY() >= tileSize * 4 - 3 * scaledPixel && playerClass.getY() < tileSize * 4 + 3 * scaledPixel
						&& map.getIndexX() + 1 <= map.getMaxIndexX()) {
						
						playerClass.setX(leftBound);
						map.loadNewRoom(1, 0);
					} else {
						playerClass.setX(rightBound);
					}
					
				}
				
				gamePanel.repaint();
				gamePanel.revalidate();
					
			}
			
			break;
			
			
		/*
		 * case KeyEvent.VK_O:
		 * 
		 * if (Gamestate.state != Gamestate.COMBAT) {
		 * 
		 * combat.startCombat(new int[] { 0, 0 });
		 * 
		 * gamePanel.repaint(); gamePanel.revalidate();
		 * 
		 * System.out.println(combat.getCombatLoss());
		 * System.out.println(combat.getCombatWin()); }
		 * 
		 * break;
		 */
			
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
			
		case KeyEvent.VK_I:
				
			gamePanel.toggleInventory();	
			
			break;
		
		}
		
		
		if (map.getIndexX() == 0 && map.getIndexY() == 2 
				&& playerClass.getX() > 3 * tileSize + 8 * scaledPixel && playerClass.getX() < 5 * tileSize 
				&& playerClass.getY() > tileSize + 8 * scaledPixel && playerClass.getY() < 3 * tileSize) {
			
			oldMan.setIsVisible(true);
			gamePanel.repaint();
			gamePanel.revalidate();
			
		} else {
			oldMan.setIsVisible(false);
		}
		
		
//		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_D) {
//
//            mapObjects.checkCollision(playerClass);
//        }
			
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