package combat;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Square {
	
	private BufferedImage image;
	private int x;
	private int y;
	private int squareSize;
	
	public Square(int x, int y, int squareSize) {
		this.x = x;
		this.y = y;
		this.squareSize = squareSize;
		
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/square.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImage() {
		return image;
	}
}
