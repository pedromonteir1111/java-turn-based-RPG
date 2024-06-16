package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.Game;
import game.ScreenSettings;

public class GameMenu implements GameStateMethods {
	
	private Game game;
	private ScreenSettings screenSettings;
	
	public GameMenu(Game game, ScreenSettings screenSettings) {
		
		this.game = game;
		this.screenSettings = screenSettings;
		
		setMenuState();
	}

	private void setMenuState() {
		
		Gamestate.state = Gamestate.MENU;
		
	}
	
	public void startingGame() {
		
		new PlayingGame(game);
	}

	@Override
	public void update() {
		
		
		
	}

	@Override
	public void draw(Graphics g2d) {
		
//		g2d.setColor(Color.black);
//		g2d.drawString("MENU", screenSettings.getScreenWidth() / 2, screenSettings.getScreenHeight() / 2);
//		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {	
		
	}

}
