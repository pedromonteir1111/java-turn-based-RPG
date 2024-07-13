package items;

import entities.Player;
import inventory.Equipable;

public class Sword extends Weapon implements Equipable {
	
	private Player player;
	private Gold gold;

	public Sword(String name, int level) {
		
		super(name, level);
		setWeaponDamage(10);
	}
	
	@Override
	public boolean equip(Player player) {
		
		if (isCollected()) {
			
			player.setAttack(player.getAttack() + getWeaponDamage());
			
			return true;
		}
		
		return false;
	}

	@Override
	public void upWeaponLevel(Weapon weapon, int necessaryGold) {
		
		if (weapon instanceof Sword) {
			
			Sword sword = (Sword) weapon;
		
			if ( ( sword.getItemLevel() - player.getLevel() ) >= 1) { // se a diferença entre o level do player e o da arma for pelo menos 1, ele poderá upá-la se tiver gold 
				
				if (gold.isSufficientGoldForUp(necessaryGold)) {
				
					sword.setWeaponDamage(getWeaponDamage() + 5); // aumenta 5 de dano a cada nível upado
					sword.setItemLevel(sword.getItemLevel() + 1); // up realizado com sucesso
				
				}
				
				else {	
					System.err.println("Ouro insuficiente para realizar o aprimoramento de " + sword.getItemName() + " !");
				}
				
			}		
		}
		
	}		

}
