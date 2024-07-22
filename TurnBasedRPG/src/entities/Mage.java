package entities;

import game.ScreenSettings;

public class Mage extends Player {
	
		public Mage(ScreenSettings screenSettings) {
			
			super(screenSettings, "/mage/mage");
			
			setMaxHealth(50);
			setAttack(35);
			setSpeed(15);
			setWalkRange(3);
			
			setX(50);
			setY(50);
			
		
	}

		@Override
		public int[] attack(int x, int y, int directionX, int directionY) {
			
			int[] attackCoords;
			
			if (directionX != 0) {
				attackCoords =  new int[] { x + 3 * directionX, y + 3 * directionY -1, x + 4 * directionX, y + 4 * directionY -1, x + 5 * directionX, y + 5 * directionY -1,
						   					x + 3 * directionX, y + 3 * directionY   , x + 4 * directionX, y + 4 * directionY   , x + 5 * directionX, y + 5 * directionY,
						   					x + 3 * directionX, y + 3 * directionY +1, x + 4 * directionX, y + 4 * directionY +1, x + 5 * directionX, y + 5 * directionY +1};
			} else {
				attackCoords =  new int[] { x + 3 * directionX -1, y + 3 * directionY, x + 4 * directionX -1, y + 4 * directionY, x + 5 * directionX -1, y + 5 * directionY,
	   										x + 3 * directionX   , y + 3 * directionY, x + 4 * directionX   , y + 4 * directionY, x + 5 * directionX   , y + 5 * directionY,
	   										x + 3 * directionX +1, y + 3 * directionY, x + 4 * directionX +1, y + 4 * directionY, x + 5 * directionX +1, y + 5 * directionY};
			}
			
			return attackCoords;
			
		}

}
