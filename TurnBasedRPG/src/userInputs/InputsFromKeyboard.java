package userInputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import combat.CombatStates;
import combat.CombatSystem;
import game.GamePanel;
//import game.MapObjects;
import gamestates.Gamestate;
import entities.Player;


public class InputsFromKeyboard implements KeyListener {
	
	private GamePanel gamePanel;
	private Player playerClass;
//	private MapObjects mapObjects;
	private CombatSystem combat;
	private int spriteIndex = 0;
	private int spriteCounter = 0;
	
	
	public InputsFromKeyboard(GamePanel gamePanel, Player playerClass, CombatSystem combat) {
		
		this.gamePanel = gamePanel;
		this.playerClass = playerClass;
		this.combat = combat;
//		this.mapObjects = mapObjects;
		
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
				
				gamePanel.repaint();
				gamePanel.revalidate();
					
				
			}
			
			break;
			
			
		case KeyEvent.VK_O:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				Gamestate.state = Gamestate.COMBAT;
				combat.createEnemies(new int[] { 0, 0 });
				combat.runCombat(-1, -1, Inputs.NONE);
				gamePanel.repaint();
				gamePanel.revalidate();
			}
			break;
			
		case KeyEvent.VK_P:
			
			combat.leaveCombat();
			gamePanel.repaint();
			gamePanel.revalidate();
			break;
			
		case KeyEvent.VK_SPACE:
			
			if (Gamestate.state == Gamestate.COMBAT) {
				
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