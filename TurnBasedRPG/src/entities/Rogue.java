package entities;

import game.ScreenSettings;

public class Rogue extends Player {
	
	public Rogue(ScreenSettings screenSettings) {
		
		super(screenSettings, "/rogue/rogue");
		
		setHealth(100);
//		setDefense();
		setSpeed(15);
		
		setX(50);
		setY(50);
		
	
}

	@Override
	public void attackQ() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attackW() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attackE() {
		// TODO Auto-generated method stub
		
	}	

}
