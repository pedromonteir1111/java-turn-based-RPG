package map;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage image;
	private boolean isCollidable = false;
	
	public Tile(BufferedImage image, boolean isCollidable) {
		
		this.image = image;
		this.isCollidable = isCollidable;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
}
