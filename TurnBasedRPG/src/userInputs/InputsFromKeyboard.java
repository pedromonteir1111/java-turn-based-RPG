package userInputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.GamePanel;
import playerClasses.Player;


public class InputsFromKeyboard implements KeyListener {
	
	private GamePanel gamePanel;
	private Player playerClass; // vai ''armazenar" qual classe o jogador escolheu (ver polimorfismo depois)
	
	
	public InputsFromKeyboard(GamePanel gamePanel, Player playerClass) {
		
		this.gamePanel = gamePanel;
		this.playerClass = playerClass;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
			
		switch(e.getKeyCode()) {	
		
		case KeyEvent.VK_W:
			
			gamePanel.changeDeltaY(-playerClass.getSpeed());
			gamePanel.setCurrentPlayerPosition(gamePanel.getPlayerPositionImage()[2]);
			break;
		
		case KeyEvent.VK_A:
			
			gamePanel.changeDeltaX(-playerClass.getSpeed());
			gamePanel.setCurrentPlayerPosition(gamePanel.getPlayerPositionImage()[1]);
			break;
			
		case KeyEvent.VK_S:
			
			gamePanel.changeDeltaY(playerClass.getSpeed());
			gamePanel.setCurrentPlayerPosition(gamePanel.getPlayerPositionImage()[0]);
			break;	
			
		case KeyEvent.VK_D:
			
			gamePanel.changeDeltaX(playerClass.getSpeed());
			gamePanel.setCurrentPlayerPosition(gamePanel.getPlayerPositionImage()[3]);
			break;
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
