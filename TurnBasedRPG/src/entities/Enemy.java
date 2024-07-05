package entities;

import game.ScreenSettings;

public abstract class Enemy extends Entity {
	
	public Enemy (ScreenSettings screenSettings, String filePath) {
		super(screenSettings, filePath);
	}
}
