package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import combat.CombatSystem;
import game.GamePanel;
import game.ScreenSettings;

public class MapGenerator {
	
	private GamePanel gamePanel;
	private ScreenSettings screenSettings;
	private CombatSystem combat;
	private Tile[] tiles;
	private int[][] tileNums;
	private Room[][] rooms;
	private int indexX, indexY;
	private int maxIndexX, maxIndexY;
	
	public MapGenerator(GamePanel gamePanel, ScreenSettings screenSettings, CombatSystem combat) {
		
		this.gamePanel = gamePanel;
		this.screenSettings = screenSettings;
		this.combat = combat;
		
		tiles = new Tile[5];
		tileNums = new int[screenSettings.getMaxCol()][screenSettings.getMaxRow()];
		
		loadTileSprites();
		
		maxIndexX = 1;
		maxIndexY = 0;
		
		rooms = new Room[maxIndexX + 1][maxIndexY + 1];
		
		rooms[0][0] = new Room("/maps/woodPlankArena.txt", false, null);
		rooms[1][0] = new Room("/maps/0test.txt", true, new int[] {0, 0, 0});
		
		loadMap(rooms[0][0].getFilePath());

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
	
	public void loadNewRoom(int x, int y) {
		
		indexX += x;
		indexY += y;
		
		if (indexX <= maxIndexX && indexY <= maxIndexY) {
			loadMap(rooms[indexX][indexY].getFilePath());
			
			if (rooms[indexX][indexY].willTriggerCombat()) {
				combat.startCombat(rooms[indexX][indexY].getEnemies());
			}
		} else {
			indexX -= x;
			indexY -= y;
		}
		
		
		
	}

	public int getIndexX() {
		return indexX;
	}

	public void setIndexX(int indexX) {
		this.indexX = indexX;
	}

	public int getIndexY() {
		return indexY;
	}

	public void setIndexY(int indexY) {
		this.indexY = indexY;
	}
	
	public int getMaxIndexX() {
		return maxIndexX;
	}
	
	public int getMaxIndexY() {
		return maxIndexY;
	}
}
