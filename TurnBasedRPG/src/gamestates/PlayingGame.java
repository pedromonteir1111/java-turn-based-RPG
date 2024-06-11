package gamestates;

import game.Game;


public class PlayingGame {
	
	private Game game;
	
	public PlayingGame(Game game) {
		
		this.game = game;
		setPlayingState();
	}

	private void setPlayingState() {
		
		Gamestate.state = Gamestate.PLAYING;
		
	}
		
	

}
