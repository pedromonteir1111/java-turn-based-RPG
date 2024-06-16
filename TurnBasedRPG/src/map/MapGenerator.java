package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game.GamePanel;
import game.ScreenSettings;

public class MapGenerator {
	
	private GamePanel gamePanel;
	private ScreenSettings screenSettings;
	private Tile[] tiles;
	private int[][] tileNums;
	
	public MapGenerator(GamePanel gamePanel, ScreenSettings screenSettings) {
		
		this.gamePanel = gamePanel;
		this.screenSettings = screenSettings;
		
		tiles = new Tile[5];
		tileNums = new int[screenSettings.getMaxCol()][screenSettings.getMaxRow()];
		
		loadTileSprites();
		loadMap("/maps/woodPlankArena.txt");
	}
	
	public void loadTileSprites() {
		
		try {
			this.tiles[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/woodPlanks1.png")), false);
			this.tiles[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/woodPlanks2.png")), false);
			this.tiles[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/woodPlanks3.png")), false);
			this.tiles[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/woodfloor.png")), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		
		String line;
		String[] numbers;
		int num;
		
		try {
			InputStream iStream = getClass().getResourceAsStream(filePath);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));
			
			for(int row = 0; row < screenSettings.getMaxRow(); row++) {
				
				line = bReader.readLine();
				
				for(int col = 0; col < screenSettings.getMaxCol(); col++) {
					numbers = line.split(" ");
					num = Integer.parseInt(numbers[col]);
					tileNums[col][row] = num;
				}
			}
			
			bReader.close();
		} catch (Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2D) {
		
		int x = 0, y = 0;
		int tileSize = screenSettings.getTileSize();
		int tileNum;
		
		for(int row = 0; row < screenSettings.getMaxRow(); row++) {
			for(int col = 0; col < screenSettings.getMaxCol(); col++) {
				tileNum = tileNums[col][row];
				g2D.drawImage(tiles[tileNum].getImage(), x, y, tileSize, tileSize, null);
				x += tileSize;
			}
			
			x = 0;
			y += tileSize;
		}
		
//		System.out.println(screenSettings.getScreenWidth()/tileSize);
//		System.out.println(screenSettings.getScreenWidth()/tileSize);
	}
}
