package playerClasses;
import game.ScreenSettings;
import weapons.Sword;

public class Warrior extends Player {
	
	private Sword warriorWeapon;
	
		public Warrior(ScreenSettings screenSettings) {
			
			super("Warrior", screenSettings);
			
			this.warriorWeapon = new Sword();
			this.warriorWeapon.setWeaponType("Espada de Aço");
			
			setHealth(100);
//			setDefense();
			setMana(0);
			setAgility(30);
			setSpeed(15);
			
			setPlayerX(50);
			setPlayerY(50);
			
		
	}	

}
