package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow {
	
	private JFrame jframe;
	
	final int width = 320;
	final int height = 180;
	final int scale = 3;
	
	final int screenWidth = width * scale;
	final int screenHeight = height * scale;
	
	
	public GameWindow(GamePanel gamePanel) {
		
		jframe = new JFrame();
		
		jframe.setPreferredSize(new Dimension(screenWidth, screenHeight));
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		
		
						
	}
	
	

}
