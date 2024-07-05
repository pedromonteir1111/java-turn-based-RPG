package entities;

import game.ScreenSettings;

public class Archer extends Player {
	
	public Archer(ScreenSettings screenSettings) {
		
		super(screenSettings, "/mage/mage");
		
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