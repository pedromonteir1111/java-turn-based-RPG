package game;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
	
	final int width = 320;
	final int height = 180;
	final int scale = 3;
	
	final int screenWidth = width * scale;
	final int screenHeight = height * scale;
	
	Thread gameThread;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}

	public void run() {
		
		while (gameThread != null) {
			
			System.out.println("Jogo rodando!");
			updateInfoOnScreen(); // recebe o comando do player e passa para a screen
			repaint(); // chamando o método 'paintComponent'
			
		}
		
	}
	
	public void updateInfoOnScreen() {
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g; // convertendo os gráficos p/ 2D propriamente
		
		g2D.setColor(Color.green);
		
		g2D.fillRect(100, 100, 48, 48);
		
		g2D.dispose();
	}
	
	
	
}
