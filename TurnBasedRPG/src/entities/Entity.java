package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.ScreenSettings;

public abstract class Entity {
	
	private int X;
	private int Y;
	private int squareX;
	private int squareY;
	private int health;
	private int attack;
	private int defense;
	private int speed;
	private int walkRange;
	private BufferedImage currentPosition;
	private BufferedImage[] upDirection, downDirection, leftDirection, rightDirection;
	private ScreenSettings screenSettings;
	private String filePath;
	
	public Entity(ScreenSettings screenSettings, String filePath) {
		
		this.filePath = filePath;
		this.screenSettings = screenSettings;
		
		importImage();
		
		this.currentPosition = downDirection[0];
		
	}
	
	private void importImage() {
		  
		  upDirection = new BufferedImage[3];
		  downDirection = new BufferedImage[3];
		  leftDirection = new BufferedImage[3];
		  rightDirection = new BufferedImage[3];
		  
		try {
	
			System.out.println(filePath + "Back2.png");
			this.upDirection[0] = ImageIO.read(getClass().getResourceAsStream(filePath + "Back2.png"));
			this.upDirection[1] = ImageIO.read(getClass().getResourceAsStream(filePath + "Back1.png"));		
			this.upDirection[2] = ImageIO.read(getClass().getResourceAsStream(filePath + "Back3.png"));			
			
			
			this.downDirection[0] = ImageIO.read(getClass().getResourceAsStream(filePath + "Front2.png")); // posição inicial
			this.downDirection[1] = ImageIO.read(getClass().getResourceAsStream(filePath + "Front1.png"));
			this.downDirection[2] = ImageIO.read(getClass().getResourceAsStream(filePath + "Front3.png"));
			
			this.rightDirection[0] = ImageIO.read(getClass().getResourceAsStream(filePath + "Right2.png"));
			this.rightDirection[1] = ImageIO.read(getClass().getResourceAsStream(filePath + "Right1.png"));
			this.rightDirection[2] = ImageIO.read(getClass().getResourceAsStream(filePath + "Right3.png"));
			
			this.leftDirection[0] = ImageIO.read(getClass().getResourceAsStream(filePath + "Left2.png"));
			this.leftDirection[1] = ImageIO.read(getClass().getResourceAsStream(filePath + "Left1.png"));
			this.leftDirection[2] = ImageIO.read(getClass().getResourceAsStream(filePath + "Left3.png"));	
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void draw(Graphics2D g2D) {
		g2D.drawImage(currentPosition, getX(), getY(), screenSettings.getTileSize(), screenSettings.getTileSize(), null);
				
	}
	
	public BufferedImage getCurrentPosition() {

		return currentPosition;
	}

	public void setCurrentPlayerPosition(BufferedImage currentPosition) {

		this.currentPosition = currentPosition;
	}

	public BufferedImage[] getUpDirection() {
		return upDirection;
	}

	public void setUpDirection(BufferedImage[] upDirection) {
		this.upDirection = upDirection;
	}

	public BufferedImage[] getDownDirection() {
		return downDirection;
	}

	public void setDownDirection(BufferedImage[] downDirection) {
		this.downDirection = downDirection;
	}

	public BufferedImage[] getLeftDirection() {
		return leftDirection;
	}

	public void setLeftDirection(BufferedImage[] leftDirection) {
		this.leftDirection = leftDirection;
	}

	public BufferedImage[] getRightDirection() {
		return rightDirection;
	}

	public void setRightDirection(BufferedImage[] rightDirection) {
		this.rightDirection = rightDirection;
	}
	
	public void changeDeltaX(int speed) {

		this.setX(getX() + speed);
	}

	public void changeDeltaY(int speed) {

		this.setY(getY() + speed);
	}
	
	public abstract void attackQ();
	
	public abstract void attackW();
	
	public abstract void attackE();

	public int getHealth() { return health; }

	public void setHealth(int health) { this.health = health; }

	public int getAttack() { return attack; }

	public void setAttack(int attack) { this.attack = attack; }

	public int getDefense() { return defense; }

	public void setDefense(int defense) { this.defense = defense; }

	public int getSpeed() { return speed; }

	public void setSpeed(int speed) { this.speed = speed; }

	public int getX() { return X; }

	public void setX(int X) { this.X = X; }

	public int getY() { return Y; }

	public void setY(int Y) { this.Y = Y; }

	public int getSquareX() { return squareX; }

	public void setSquareX(int X) { this.squareX = X; }

	public int getSquareY() { return squareY; }

	public void setSquareY(int Y) { this.squareY = Y; }

	public int getWalkRange() { return walkRange; }

	public void setWalkRange(int walkRange) { this.walkRange = walkRange; }
}
