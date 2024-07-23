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
import entities.Mage;
import entities.OldMan;
import entities.Player;
import entities.Rogue;
import entities.Warrior;
import gamestates.GameMenu;
import gamestates.Gamestate;
import inventory.Equipable;
import inventory.PlayerInventory;
import inventory.Usable;
import items.Elixir;
import items.Item;
import items.Sword;
import items.WarriorArmor;
import userInputs.Mouse;

public class Game {
	
	private ScreenSettings screenSettings;
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	
	private Player player;
	private Player mage;
	private Player rogue;
	private PlayerInventory playerInventory;
	private Elixir elixir;
	private OldMan oldMan;
	
	// \/ para mudar imagem do cursor \/
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private BufferedImage cursorImage;
	private Cursor c;
	
	public Game() {
		
		screenSettings = new ScreenSettings(5);
		
		initObjects();
		initInventory();
		
		gameWindow = new GameWindow(gamePanel, screenSettings);
		
		gamePanel.setBackground(Color.black);
		gamePanel.setCursor(c);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		
	}
	
	public void initObjects() {
		
		player = new Warrior(screenSettings);
		mage = new Mage(screenSettings);
		rogue = new Rogue(screenSettings);
		oldMan = new OldMan(screenSettings);
		playerInventory = new PlayerInventory(10);
		gamePanel = new GamePanel(player, mage, rogue, screenSettings, playerInventory, oldMan);
		
		
		try {
			cursorImage = ImageIO.read(getClass().getResourceAsStream("/ui/cursor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		c = toolkit.createCustomCursor(cursorImage , new Point(gamePanel.getX(), gamePanel.getY()), "img");
			
	}
	
	public void initInventory() {
		
		Item initialSword = new Sword("Espada de Prata", 1);
		playerInventory.addItem(initialSword);
		
		Item initialArmor = new WarriorArmor("Armadura de Guerra", 1);
		playerInventory.addItem(initialArmor);
		
		elixir = new Elixir("Elixir Milagroso", 0);
		playerInventory.addItem(elixir);

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
}