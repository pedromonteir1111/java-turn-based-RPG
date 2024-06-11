package game;

import java.awt.Color;

import combat.BattleGrid;
import gamestates.GameMenu;
import gamestates.Gamestate;
import playerClasses.Player;
import playerClasses.Warrior;

public class Game implements Runnable {
	
	private ScreenSettings screenSettings;
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private Player player;
	private BattleGrid battleGrid;
	private GameMenu gameMenu;
	private final int FPS_SET = 120;
//	private InputsFromKeyboard inputsFromKeyboard = new InputsFromKeyboard(gamePanel, player);
	
	public Game() {
		
		screenSettings = new ScreenSettings(4);
		
		initObjects();
		
		gameWindow = new GameWindow(gamePanel, screenSettings);
		
		gamePanel.setBackground(Color.black);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		
				
		startGameLoop();	
		
	}
	
	public void initObjects() {
		
		player = new Warrior();
		battleGrid = new BattleGrid(screenSettings);
		gamePanel = new GamePanel(player, screenSettings, battleGrid);
		gameMenu = new GameMenu(this, screenSettings);
			
		}
	
	private void startGameLoop() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void update() {
		
		switch (Gamestate.state) {
			case MENU:
				handleMenu();
				break;
				
			case PLAYING:
				// animação do player e eventos do jogo
				break;
				
		default:
			break;
			
		}
		
	}
	
	
	private void handleMenu() {
		
		gameMenu.draw(gamePanel.getGraphics());
		
		System.out.println("MENU");
		
	}

	@Override
	public void run() {
		
		double timeNanoPerFrame = 1000000000.0 / FPS_SET; // calcula quantos ns são necessários p/ 120 frames serem exibidos
		
		long previousTime = System.nanoTime();
		
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaFrame = 0;
		
		
		while (true) {
			
			long currentTime = System.nanoTime(); // inicializa o tempo atual a cada iteração do loop 
			
			deltaFrame += (currentTime - previousTime) / timeNanoPerFrame;
			previousTime = currentTime;
			
			// quando deltaFrame ultrapassar o valor 1, quer dizer que o tempo necessário para atualizar 1 frame e manter o jogo a 120fps foi superado, daí reseta
			
			if (deltaFrame >= 1) {
			
				gamePanel.repaint();
				frames++;
				deltaFrame--;
		
			}
			
			// contador de fps (estável a 120 frames)
			if (System.currentTimeMillis() - lastCheck >= 1000) { 
				
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: "+frames);
				
				frames = 0;
			}
		}
		
	}
	
}