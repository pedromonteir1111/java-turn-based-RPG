package entities;

import game.ScreenSettings;

public class Goblin extends Enemy {
	
		public Goblin(ScreenSettings screenSettings) { 
			super(screenSettings, "/goblin/goblin");
		}

		@Override
public int[] attack(int x, int y, int directionX, int directionY) {
			
			return new int[] { (x + 1) * directionX, (y + 1) * directionY, (x + 2) * directionX, (y + 2) * directionY };
			
		}
}
