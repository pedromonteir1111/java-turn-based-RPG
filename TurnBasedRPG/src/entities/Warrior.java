package entities;
import game.ScreenSettings;
import items.Sword;

public class Warrior extends Player {
	
	private Sword warriorWeapon;
	
		public Warrior(ScreenSettings screenSettings) {
			
			super(screenSettings, "/knight/knight");
			
			this.warriorWeapon = new Sword("Espada de aço", 0);
			
			setHealth(100);
//			setDefense();
			setSpeed(15);
			setWalkRange(3 );
			
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
