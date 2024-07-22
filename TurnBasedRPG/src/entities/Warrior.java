package entities;
import game.ScreenSettings;
import items.Sword;

public class Warrior extends Player {
	
	private Sword warriorWeapon;
	
		public Warrior(ScreenSettings screenSettings) {
			
			super(screenSettings, "/knight/knight");
			
			this.warriorWeapon = new Sword("Espada de aço", 0);
			
			setMaxHealth(150);
			setAttack(20);
			setSpeed(15);
			setWalkRange(4);
			
			setX(50);
			setY(50);
			
		
	}

		@Override
		public int[] attack(int x, int y, int directionX, int directionY) {
			
			int[] attackCoords;
			
			if (directionX != 0) {
				attackCoords =  new int[] { x + 1 * directionX, y + 1 * directionY -1,
						   					x + 1 * directionX, y + 1 * directionY   ,
						   					x + 1 * directionX, y + 1 * directionY +1};
			} else {
				attackCoords =  new int[] { x + 1 * directionX -1, y + 1 * directionY,
	   										x + 1 * directionX   , y + 1 * directionY,
	   										x + 1 * directionX +1, y + 1 * directionY,};
			}
			
			return attackCoords;
			
		}

}
