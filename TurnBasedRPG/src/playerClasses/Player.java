package playerClasses;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.ScreenSettings;

public class Player {
	
	private ScreenSettings screenSettings;
	private String playerClass;
	private int playerX;
	private int playerY;
	private int squareX;
	private int squareY;
	private BufferedImage[] playerPositionImage;
	private BufferedImage currentPlayerPosition;
	
	private int health;
	private int attack;
	private int defense;
	private int mana;
	private int agility; // quanto maior a agilidade, maior a chance de acertar o ataque
	private int speed;
	private boolean dead;
	
	public Player(String playerClass, ScreenSettings screenSettings) {
		
		this.playerClass = playerClass;
		this.screenSettings = screenSettings;
		importImage();
		
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
	
	public void draw(Graphics2D g2D) {
		g2D.drawImage(currentPlayerPosition, playerX, playerY, screenSettings.getTileSize(), screenSettings.getTileSize(), null);
	}
	
	public void changeDeltaX(int speed) {
		
		this.playerX += speed;
	}
	
	public void changeDeltaY(int speed) {
		
		this.playerY += speed;
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
	
	
	public String getPlayerClass() {
		
		return playerClass;
	}
	
	public int getHealth() {
		
		return health;
	}
	
	public void setHealth(int health) {
		
		this.health = health;
	}
	
	public int getAttack() {
		
		return attack;
	}
	
	public void setAttack(int attack) {
		
		this.attack = attack;
	}
	
	public int getDefense() {
		
		return defense;
	}
	
	public void setDefense(int defense) {
		
		this.defense = defense;
	}
	
	public int getMana() {
		
		return mana;
	}
	
	public void setMana(int mana) {
		
		this.mana = mana;
	}
	
	public int getAgility() {
		
		return agility;
	}
	
	public void setAgility(int agility) {
		
		this.agility = agility;
	}
	
	public boolean isDead() {
		
		return dead;
	}
	
	public void setDead(boolean dead) {
		
		this.dead = dead;
	}


	public int getSpeed() {
		
		return speed;
	}


	public void setSpeed(int speed) {
		
		this.speed = speed;
	}


	public int getPlayerX() {
		
		return playerX;
	}


	public void setPlayerX(int playerX) {
		
		this.playerX = playerX;
	}


	public int getPlayerY() {
		
		return playerY;
	}


	public void setPlayerY(int playerY) {
		
		this.playerY = playerY;
	}

	public int getSquareX() {
		return squareX;
	}

	public void setSquareX(int squareX) {
		this.squareX = squareX;
	}

	public int getSquareY() {
		return squareY;
	}

	public void setSquareY(int squareY) {
		this.squareY = squareY;
	}	
}
