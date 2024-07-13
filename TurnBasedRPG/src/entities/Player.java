package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.ScreenSettings;

public abstract class Player extends Entity {
	
	private int gold;

	public Player(ScreenSettings screenSettings, String filePath) {

		super(screenSettings, filePath);
		setGold(50);
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	
	// para ser utilizado nas colisões (delimitação da área)
	
	/*
	 * public Rectangle getPlayerBounds() { return new Rectangle(playerX, playerY,
	 * screenSettings.getScreenWidth(), screenSettings.getScreenHeight());
	 * 
	 * }
	 */




	

}
