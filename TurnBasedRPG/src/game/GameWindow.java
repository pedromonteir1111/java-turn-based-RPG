package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow {
	
	private JFrame jframe;	
	
	public GameWindow(GamePanel gamePanel, ScreenSettings settings) {
		
		jframe = new JFrame();
		
		jframe.setPreferredSize(new Dimension(settings.getScreenWidth(), settings.getScreenHeight()));
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		
		
						
	}
	
	

}
