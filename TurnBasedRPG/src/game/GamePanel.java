package game;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class GamePanel extends JPanel{
	
	final int width = 320;
	final int height = 180;
	final int scale = 3;
	
	final int screenWidth = width * scale;
	final int screenHeight = height * scale;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
}
