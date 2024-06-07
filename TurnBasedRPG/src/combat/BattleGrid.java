package combat;

import java.awt.Graphics2D;

import game.ScreenSettings;

public class BattleGrid {
	
	private ScreenSettings screenSettings;
	private Square[][] squares;
	
	public BattleGrid(ScreenSettings screenSettings) {
		
		int tileSize = screenSettings.getTileSize();
		
		this.screenSettings = screenSettings;
		this.squares = new Square[16][7];
		
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 7; j++) {
				squares[i][j] = new Square(i * tileSize + 2 * tileSize, j * tileSize + tileSize, tileSize);
			}
		}
	}
	
	public void draw(Graphics2D g2D) {
		
		int x = 0, y = 0;
		int tileSize = screenSettings.getTileSize();
		
		x = tileSize * 2;
		y = tileSize;

		for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 16; col++) {
				g2D.drawImage(squares[col][row].getImage(), x, y, tileSize, tileSize, null);
				x += tileSize;
			}
				
			x = tileSize * 2;
			y += tileSize;
		}
	}
	
}
