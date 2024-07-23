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
	private boolean isInWalkRange;

	private String currentState;
	
	private Player currentPlayer;
	private Enemy currentEnemy;
	
	public Square(int x, int y, int relativeX, int relativeY) {
		this.x = x;
		this.y = y;
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		this.currentState = "";
		this.setInWalkRange(false);
		
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/square.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setImage(String state) {
		
		switch (state) {
		
			case "Range":
				currentState = state;
				break;
		
			case "Warrior":
				currentState = state;
				break;
				
			case "Mage":
				currentState = state;
				break;
				
			case "Archer":
				currentState = state;
				break;
				
			case "Rogue":
				currentState = state;
				break;
		
			case "Hover":
				if(!currentState.equals("Selected")) {
					currentState = state;
				}
				break;
				
			case "HoverRange":
				if(!currentState.equals("Selected")) {
					currentState = state;
				}
				break;
				
			case "Selected":
				currentState = state;
				break;
				
			case "SelectedRange":
				currentState = state;
				break;
				
			case "SelectedRed":
				currentState = state;
				break;
				
			case "Red":
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
	
	public void emptySquare() {
		this.currentEnemy = null;
		this.currentPlayer = null;
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

	public boolean isInWalkRange() {
		return isInWalkRange;
	}

	public void setInWalkRange(boolean isInWalkRange) {
		this.isInWalkRange = isInWalkRange;
	}
}
