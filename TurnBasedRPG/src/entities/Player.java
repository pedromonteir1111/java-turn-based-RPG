package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.ScreenSettings;

public abstract class Player extends Entity {

	private ScreenSettings screenSettings;
//	private BufferedImage[] playerPositionImage;
	private BufferedImage currentPlayerPosition;
	private BufferedImage[] upDirection, downDirection, leftDirection, rightDirection;

	public Player(ScreenSettings screenSettings) {

		this.screenSettings = screenSettings;
		importImage();
		this.currentPlayerPosition = downDirection[0];
		

	}

	private void importImage() {
		  
		  upDirection = new BufferedImage[3];
		  downDirection = new BufferedImage[3];
		  leftDirection = new BufferedImage[3];
		  rightDirection = new BufferedImage[3];
		  
		try {
	
			this.upDirection[0] = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack2.png"));
			this.upDirection[1] = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack1.png"));		
			this.upDirection[2] = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack3.png"));			
			
			
			this.downDirection[0] = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront2.png")); // posição inicial
			this.downDirection[1] = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront1.png"));
			this.downDirection[2] = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront3.png"));
			
			this.rightDirection[0] = ImageIO.read(getClass().getResourceAsStream("/knight/knightRight2.png"));
			this.rightDirection[1] = ImageIO.read(getClass().getResourceAsStream("/knight/knightRight1.png"));
			this.rightDirection[2] = ImageIO.read(getClass().getResourceAsStream("/knight/knightRight3.png"));
			
			this.leftDirection[0] = ImageIO.read(getClass().getResourceAsStream("/knight/knightLeft2.png"));
			this.leftDirection[1] = ImageIO.read(getClass().getResourceAsStream("/knight/knightLeft1.png"));
			this.leftDirection[2] = ImageIO.read(getClass().getResourceAsStream("/knight/knightLeft3.png"));	
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	// para ser utilizado nas colisões (delimitação da área)
	
	/*
	 * public Rectangle getPlayerBounds() { return new Rectangle(playerX, playerY,
	 * screenSettings.getScreenWidth(), screenSettings.getScreenHeight());
	 * 
	 * }
	 */
	public void draw(Graphics2D g2D) {
		g2D.drawImage(currentPlayerPosition, getX(), getY(), screenSettings.getTileSize(), screenSettings.getTileSize(), null);
				
	}

	public void changeDeltaX(int speed) {

		this.setX(getX() + speed);
	}

	public void changeDeltaY(int speed) {

		this.setY(getY() + speed);
	}

	public BufferedImage getCurrentPlayerPosition() {

		return currentPlayerPosition;
	}

	public void setCurrentPlayerPosition(BufferedImage currentPlayerPosition) {

		this.currentPlayerPosition = currentPlayerPosition;
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

}
