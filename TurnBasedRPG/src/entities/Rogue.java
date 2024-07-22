package entities;

import game.ScreenSettings;

public class Rogue extends Player {
	
	public Rogue(ScreenSettings screenSettings) {
		
		super(screenSettings, "/rogue/rogue");
		
		setMaxHealth(75);
		setAttack(40);
		setSpeed(15);
		setWalkRange(6);
		
		setX(50);
		setY(50);	
	
}

	@Override
	public int[] attack(int x, int y, int directionX, int directionY) {
		
		return new int[] { x + 1 * directionX, y + 1 * directionY };
		
	}

}
