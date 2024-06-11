package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface GameStateMethods {
	
	public void update();
	
	public void draw(Graphics g2D);
	
	public void keyPressed(KeyEvent e);
	
	public void keyReleased(KeyEvent e);

}
