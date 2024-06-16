package userInputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import combat.CombatSystem;
import game.GamePanel;
import gamestates.Gamestate;
import playerClasses.Player;


public class InputsFromKeyboard implements KeyListener {
	
	private GamePanel gamePanel;
	private Player playerClass;
	CombatSystem combat;
	
	
	public InputsFromKeyboard(GamePanel gamePanel, Player playerClass, CombatSystem combat) {
		
		this.gamePanel = gamePanel;
		this.playerClass = playerClass;
		this.combat = combat;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
			
		switch(e.getKeyCode()) {	
		
		case KeyEvent.VK_W:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				playerClass.changeDeltaY(-playerClass.getSpeed());
				playerClass.setCurrentPlayerPosition(playerClass.getPlayerPositionImage()[2]);
				gamePanel.repaint();
				gamePanel.revalidate();
			}
			break;
		
		case KeyEvent.VK_A:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				playerClass.changeDeltaX(-playerClass.getSpeed());
				playerClass.setCurrentPlayerPosition(playerClass.getPlayerPositionImage()[1]);
				gamePanel.repaint();
				gamePanel.revalidate();
			}
			break;
			
		case KeyEvent.VK_S:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				playerClass.changeDeltaY(playerClass.getSpeed());
				playerClass.setCurrentPlayerPosition(playerClass.getPlayerPositionImage()[0]);
				gamePanel.repaint();
				gamePanel.revalidate();
			}
			break;	
			
		case KeyEvent.VK_D:
			
			if (Gamestate.state != Gamestate.COMBAT) {
				playerClass.changeDeltaX(playerClass.getSpeed());
				playerClass.setCurrentPlayerPosition(playerClass.getPlayerPositionImage()[3]);
				gamePanel.repaint();
				gamePanel.revalidate();
			}
			break;
			
		case KeyEvent.VK_O:
			
			Gamestate.state = Gamestate.COMBAT;
			combat.runCombat(0, 0, false);
			gamePanel.repaint();
			gamePanel.revalidate();
			break;
			
		case KeyEvent.VK_P:
			
			Gamestate.state = Gamestate.PLAYING;
			gamePanel.repaint();
			gamePanel.revalidate();
			break;	
		
		}
		
		
		
//		case KeyEvent.VK_ENTER:
//			
//			gamePanel.startGame();
//			break;		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
