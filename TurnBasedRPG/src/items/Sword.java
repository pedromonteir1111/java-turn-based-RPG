package items;

import entities.Entity;

public class Sword extends Weapon {
	
	private Entity player;

	public Sword(String name, int level) {
		
		super(name, level);
		setWeaponDamage(10);
	}

	@Override
	public void upWeaponLevel(Weapon weapon) {
		
		if (weapon instanceof Sword) {
			
			Sword sword = (Sword) weapon;
		
		if ( ( sword.getItemLevel() - player.getLevel() ) >= 1) { // se a diferença entre o level do player e o da arma for pelo menos 1, ele poderá upá-la se tiver gold 
			
			sword.setWeaponDamage(getWeaponDamage() + 5); // aumenta 5 de dano a cada nível upado
		}
		
		}
		
	}
	
	
	
		
	
	
	
	
	
		
		

}
