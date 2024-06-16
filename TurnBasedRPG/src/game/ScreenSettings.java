package game;

public class ScreenSettings {
	
	private int width = 320;
	private int height = 180;
	private int scale;
	
	private int screenWidth;
	private int screenHeight;
	private int tileSize;
	
	public ScreenSettings(int scale) {
		
		this.setScale(scale);
		this.screenWidth = width * scale;
		this.screenHeight = height * scale;
		this.tileSize = 16 * scale;
	}
	
	public int getMaxCol() {
		return screenWidth/tileSize;
	}
	
	public int getMaxRow() {
		return screenHeight/tileSize;
	}
	
	public int getScreenWidth() {
		return this.screenWidth;
	}
	
	public int getScreenHeight() {
		return this.screenHeight;
	}

	public int getTileSize() {
		return this.tileSize;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
		this.screenWidth = width * scale;
		this.screenHeight = height * scale;
		this.tileSize = 16 * scale;
	}
}
