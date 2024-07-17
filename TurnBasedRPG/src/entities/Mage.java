package entities;

import game.ScreenSettings;

public class Mage extends Player {
	
		public Mage(ScreenSettings screenSettings) {
			
			super(screenSettings, "/mage/mage");
			
			setHealth(100);
//			setDefense();
			setSpeed(15);
			
			setX(50);
			setY(50);
			
		
	}

		@Override
		public int[] attack(int x, int y, int directionX, int directionY) {
			
			return new int[] { (x + 1) * directionX, (y + 1) * directionY, (x + 2) * directionX, (y + 2) * directionY };
			
		}

}
