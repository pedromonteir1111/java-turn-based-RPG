package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.ScreenSettings;

public class OldMan {

	private BufferedImage sprite, dialog;
	private ScreenSettings settings;
	private int tileSize;
	private boolean isVisible, gameWillEnd;
	
	public OldMan(ScreenSettings settings) {
		
		this.settings = settings;
		this.tileSize = settings.getTileSize(); 
		this.isVisible = false;
		
		try {
			this.sprite = ImageIO.read(getClass().getResourceAsStream("/ui/oldman.png"));
			this.dialog = ImageIO.read(getClass().getResourceAsStream("/ui/dialog.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2D, int gold) {
		g2D.drawImage(sprite, tileSize *4, tileSize * 2, tileSize, tileSize, null);
		
		if(gameWillEnd) {
			endGame(g2D);
		}
		
		if(gold >= 400 && isVisible) {
			g2D.drawImage(sprite, settings.getScreenWidth()/2 - tileSize * 3, settings.getScreenHeight()/2 - tileSize * 10, tileSize * 10, tileSize * 3, null);
			gameWillEnd = true;
		} else if(isVisible) {
			g2D.drawImage(dialog, tileSize * 5, settings.getScreenHeight() - tileSize * 3 - 7 * settings.getScale(), tileSize * 10, tileSize * 3, null);
		}
		
	}
	
	public void setIsVisible(boolean isVisible) {
		
		this.isVisible = isVisible;
		
	}
	
	public void endGame(Graphics2D g2D) {
		
		System.out.println("Parabens, voce conseguiu escapar");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		g2D.dispose();
		System.exit(0);

	}
}
