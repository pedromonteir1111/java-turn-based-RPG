package combat;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.ScreenSettings;

public class HUD {

	private BufferedImage[] hudImages, icons;
	private int currentPlayer, currentHud;
	private ScreenSettings screenSettings;
	
	public HUD(ScreenSettings screenSettings) {
		
		this.screenSettings = screenSettings;
		this.setCurrentHud(0);
		this.setCurrentPlayer(0);
		
		importImages();		
	}

	private void importImages() {
		
		this.hudImages = new BufferedImage[3];
		this.icons = new BufferedImage[3];
		
		try {
			this.hudImages[0] = ImageIO.read(getClass().getResourceAsStream("/hud/hud1.png"));
			this.hudImages[1] = ImageIO.read(getClass().getResourceAsStream("/hud/hud2.png"));
			this.hudImages[2] = ImageIO.read(getClass().getResourceAsStream("/hud/hud3.png"));
			
			this.icons[0] = ImageIO.read(getClass().getResourceAsStream("/hud/knightIcon.png"));
			this.icons[1] = ImageIO.read(getClass().getResourceAsStream("/hud/mageIcon.png"));
			this.icons[2] = ImageIO.read(getClass().getResourceAsStream("/hud/rogueIcon.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2D) {
		g2D.drawImage(hudImages[currentHud], screenSettings.getTileSize() * 8,  screenSettings.getScreenHeight() - screenSettings.getTileSize() * 3 - 7 * screenSettings.getScale(), screenSettings.getTileSize() * 7, screenSettings.getTileSize() * 3, null);
		g2D.drawImage(icons[currentPlayer], screenSettings.getTileSize() * 5, screenSettings.getScreenHeight() - screenSettings.getTileSize() * 3 - 7 * screenSettings.getScale(), screenSettings.getTileSize() * 3, screenSettings.getTileSize() * 3, null);
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getCurrentHud() {
		return currentHud;
	}

	public void setCurrentHud(int currentHud) {
		this.currentHud = currentHud;
	}
	
}
