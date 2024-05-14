package map;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.GamePanel;
import game.ScreenSettings;

public class MapGenerator {
	
	GamePanel gamePanel;
	Tile[] tiles;
	
	public MapGenerator(GamePanel gamePanel) {
		
		this.gamePanel = gamePanel;
		
		tiles = new Tile[5];
		
		getTileSprites();
	}
	
	public void getTileSprites() {
		
		try {
			this.tiles[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/woodfloor.png")), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2D, ScreenSettings screenSettings) {
		
		int x = 0, y = 0;
		
		for(int row = 0; row < screenSettings.getScreenHeight()/16; row++) {
			for(int col = 0; col < screenSettings.getScreenWidth()/16 + 1; col++) {
				g2D.drawImage(tiles[0].getImage(), x, y, screenSettings.getTileSize(), screenSettings.getTileSize(), null);
				x += screenSettings.getTileSize();
			}
			
			x = 0;
			y += screenSettings.getTileSize();
		}
	}
}
