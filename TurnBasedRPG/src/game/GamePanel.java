package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import map.MapGenerator;
import playerClasses.Player;
import userInputs.InputsFromKeyboard;

public class GamePanel extends JPanel {
	
	private Player playerClass;
	private ScreenSettings screenSettings;
	private Timer animationTimer;
	
	private int deltaX;
	private int deltaY;
	private int currentIdleFrame;
	private int idleAnimationTick;
	private final int IDLE_TICK_RATE = 10;
	private int frames = 0;
	private long lastCheck = 0;
	
	private InputsFromKeyboard inputsFromKeyboard;
	private BufferedImage[] playerPositionImage;
	private BufferedImage currentPlayerPosition;
	
	private MapGenerator mapGenerator = new MapGenerator(this);
//	private BufferedImage tempBackground;
	
	
	public GamePanel(Player playerClass, ScreenSettings screenSettings) {
		
		this.playerClass = playerClass;
		this.screenSettings = screenSettings; 
		
		importImage();
		
		inputsFromKeyboard = new InputsFromKeyboard(this, playerClass);
		this.addKeyListener(inputsFromKeyboard);
		
		animationTimer = new Timer(100, e -> updateAnimations()); // as animações são atualizadas a cada 100ms
		animationTimer.start();
		
	
	}
	
	private void importImage() {
		
		playerPositionImage = new BufferedImage[4];
		
		try {
			this.playerPositionImage[0] = ImageIO.read(getClass().getResourceAsStream("/knightFront.png"));
			this.playerPositionImage[1] = ImageIO.read(getClass().getResourceAsStream("/knightLeft.png"));
			this.playerPositionImage[2] = ImageIO.read(getClass().getResourceAsStream("/knightBack.png"));
			this.playerPositionImage[3] = ImageIO.read(getClass().getResourceAsStream("/knightRight.png"));
			
			currentPlayerPosition = this.playerPositionImage[0];
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public void updateAnimations() {
		
		if (!playerClass.isMoving()) {
			
			idleAnimationTick++;
			
			if (idleAnimationTick >= IDLE_TICK_RATE) {
				
				currentIdleFrame = (currentIdleFrame + 1) % 4; // os frames são atualizados dentro do limite do array playerPositionImage, que tem as 4 direções p/ representar o player
				currentPlayerPosition = playerPositionImage[currentIdleFrame];
				
				idleAnimationTick = 0; // resetar o contador de ticks p/ a próxima checagem
			}
			
			repaint();
		}
		
		else {
			
			idleAnimationTick = 0; // se o jogador está movendo, não há ticks de espera p/ serem contados
			currentIdleFrame = playerClass.getDirection(); // o frame atual se torna aquele que tem a exata mesma direção do movimento
			currentPlayerPosition = playerPositionImage[currentIdleFrame];
		}
			
		
	}


	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g; // convertendo os gráficos p/ 2D propriamente
		
		g2D.setColor(Color.green);
		
		mapGenerator.draw(g2D, screenSettings);
		g2D.drawImage(currentPlayerPosition, playerClass.getPlayerX() + deltaX, playerClass.getPlayerY() + deltaY, screenSettings.getTileSize(), screenSettings.getTileSize(), null);
//		g2D.drawImage(tempBackground, 0, 0, 48, 48, null);
		
		g2D.dispose();
		
		frames++;
		
		if (System.currentTimeMillis() - lastCheck >= 1000) {
			
			lastCheck = System.currentTimeMillis();
			System.out.println("FPS = "+frames);
			
			frames = 0;
		}
		
		
	}
	
	
	public void changeDeltaX(int speed) {
		
		this.deltaX += speed;
		repaint();
	}
	
	public void changeDeltaY(int speed) {
		
		this.deltaY += speed;
		repaint();
	}


	public BufferedImage getCurrentPlayerPosition() {
		
		return currentPlayerPosition;
	}


	public void setCurrentPlayerPosition(BufferedImage currentPlayerPosition) {
		
		this.currentPlayerPosition = currentPlayerPosition;
	}

	
	public BufferedImage[] getPlayerPositionImage() {
		
		return playerPositionImage;
	}
	
	
	public void setPlayerPositionImage(BufferedImage[] playerPositionImage) {
		
		this.playerPositionImage = playerPositionImage;
	}
	
}
	
	