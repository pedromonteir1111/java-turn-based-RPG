package combat;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Enemy;
import entities.Player;

public class Square {
	
	private BufferedImage image;
	private int x;
	private int y;
	private int relativeX;
	private int relativeY;

	private String currentState;
	
	private Player currentPlayer;
	private Enemy currentEnemy;
	
	public Square(int x, int y, int relativeX, int relativeY) {
		this.x = x;
		this.y = y;
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		this.currentState = "";
		
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/square.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setImage(String state) {
		
		switch (state) {
			case "Hover":
				if(!currentState.equals("Selected")) {
					currentState = state;
				}
				break;
				
			case "Selected":
				currentState = state;
				break;
				
			case "":
				currentState = state;
				break;
		}
		
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/square" + currentState + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Enemy getCurrentEnemy() {
		return currentEnemy;
	}

	public void setCurrentEnemy(Enemy currentEnemy) {
		this.currentEnemy = currentEnemy;
	}

	public int getRelativeX() {
		return relativeX;
	}

	public int getRelativeY() {
		return relativeY;
	}
}
