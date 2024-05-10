package playerClasses;
import weapons.Sword;

public class Warrior extends Player {
	
	private Sword warriorWeapon;
	
		public Warrior() {
			
			super("Warrior");
			
			this.warriorWeapon = new Sword();
			this.warriorWeapon.setWeaponType("Espada de Aço");
			
			setHealth(100);
//			setDefense();
			setMana(0);
			setAgility(30);
			
		
	}	

}
