package entities;
import game.ScreenSettings;
import items.Sword;

public class Warrior extends Player {
	
	private Sword warriorWeapon;
	
		public Warrior(ScreenSettings screenSettings) {
			
			super(screenSettings, "/knight/knight");
			
			this.warriorWeapon = new Sword("Espada de aço", 0);
			
			setMaxHealth(100);
			setAttack(20);
			setSpeed(15);
			setWalkRange(4);
			
			setX(50);
			setY(50);
			
		
	}

		@Override
		public int[] attack(int x, int y, int directionX, int directionY) {
			
			return new int[] { x + 1 * directionX, y + 1 * directionY, x + 2 * directionX, y + 2 * directionY };
			
		}

}
