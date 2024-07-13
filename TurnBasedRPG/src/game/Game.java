package game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import combat.CombatSystem;
import entities.Player;
import entities.Warrior;
import gamestates.GameMenu;
import gamestates.Gamestate;
import inventory.Equipable;
import inventory.PlayerInventory;
import inventory.Usable;
import items.Elixir;
import items.Item;
import userInputs.Mouse;

public class Game {
	
	private ScreenSettings screenSettings;
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	
	private Player player;
	private PlayerInventory playerInventory;
//	private Elixir elixir;
	
	// \/ para mudar imagem do cursor \/
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private BufferedImage cursorImage;
	private Cursor c;
//	private InputsFromKeyboard inputsFromKeyboard = new InputsFromKeyboard(gamePanel, player);
	
	public Game() {
		
		screenSettings = new ScreenSettings(5);
		
		initObjects();
		
		gameWindow = new GameWindow(gamePanel, screenSettings);
		
		gamePanel.setBackground(Color.black);
		gamePanel.setCursor(c);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		
	}
	
	public void initObjects() {
		
		player = new Warrior(screenSettings);
		gamePanel = new GamePanel(player, screenSettings);
		playerInventory = new PlayerInventory(10);
//		elixir = new Elixir("Elixir Milagroso", -1);
		
		
		try {
			cursorImage = ImageIO.read(getClass().getResourceAsStream("/ui/cursor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		c = toolkit.createCustomCursor(cursorImage , new Point(gamePanel.getX(), gamePanel.getY()), "img");
			
	}
	
	public void collectItem(Item item) {
		
		if (!item.isCollected()) {
			playerInventory.addItem(item);
		}
	}
	
	public void useItem(Item item) {
		
		playerInventory.selectItem(item);
		
		if (item instanceof Usable) {
			
			((Usable) item).use(player);
			
			playerInventory.removeItem(item);
		}
		
		else if (item instanceof Equipable) {
			
			((Equipable) item).equip(player);
			
		}
			
	}
	
	public void printInventory() {
		
		System.out.println("Seu inventário: ");
		
		for (Item item : playerInventory.getItems()) {
			System.out.println(item.getItemName() + " ~~~ Level: " + item.getItemLevel());
		}
	}
}