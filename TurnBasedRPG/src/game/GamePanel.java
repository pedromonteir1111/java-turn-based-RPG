package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import combat.BattleGrid;
import gamestates.Gamestate;
import map.MapGenerator;
import playerClasses.Player;
import userInputs.InputsFromKeyboard;

public class GamePanel extends JPanel {
	
	private Player playerClass;
	private ScreenSettings screenSettings;
	private BattleGrid battleGrid;
	
	private int deltaX;
	private int deltaY;
	private InputsFromKeyboard inputsFromKeyboard;
	private BufferedImage[] playerPositionImage;
	private BufferedImage currentPlayerPosition;
	
	private MapGenerator mapGenerator;
	
	public GamePanel(Player playerClass, ScreenSettings screenSettings, BattleGrid battleGrid) {
		
		this.playerClass = playerClass;
		this.screenSettings = screenSettings; 
		this.battleGrid = battleGrid;
		
		importImage();
		
		mapGenerator = new MapGenerator(this, screenSettings);
		inputsFromKeyboard = new InputsFromKeyboard(this, playerClass);
		this.addKeyListener(inputsFromKeyboard);
	
	}
	
	
	private void importImage() {
		
		playerPositionImage = new BufferedImage[4];
		
		try {
			this.playerPositionImage[0] = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront.png"));
			this.playerPositionImage[1] = ImageIO.read(getClass().getResourceAsStream("/knight/knightLeft.png"));
			this.playerPositionImage[2] = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack.png"));
			this.playerPositionImage[3] = ImageIO.read(getClass().getResourceAsStream("/knight/knightRight.png"));
			
			currentPlayerPosition = this.playerPositionImage[0];
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g; // convertendo os gráficos p/ 2D propriamente
		
		mapGenerator.draw(g2D);
		if (playerClass.isInCombat()) {
			battleGrid.draw(g2D);
		}
		
		g2D.drawImage(currentPlayerPosition, playerClass.getPlayerX() + deltaX, playerClass.getPlayerY() + deltaY, screenSettings.getTileSize(), screenSettings.getTileSize(), null);
		
		g2D.dispose();
	}
	
	
	public void changeDeltaX(int speed) {
		
		this.deltaX += speed;
		repaint();
	}
	
	public void changeDeltaY(int speed) {
		
		this.deltaY += speed;
		repaint();
	}


	public BufferedImage getCurrentPlayerPosition() {
		
		return currentPlayerPosition;
	}


	public void setCurrentPlayerPosition(BufferedImage currentPlayerPosition) {
		
		this.currentPlayerPosition = currentPlayerPosition;
	}

	
	public BufferedImage[] getPlayerPositionImage() {
		
		return playerPositionImage;
	}
	
	
	public void setPlayerPositionImage(BufferedImage[] playerPositionImage) {
		
		this.playerPositionImage = playerPositionImage;
	}
	
}
	
	