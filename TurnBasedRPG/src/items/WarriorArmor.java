package items;

import entities.Player;
import inventory.Equipable;

public class WarriorArmor extends Armor implements Equipable {
	
	private Player player;
	private Gold gold;

	public WarriorArmor(String name, int level) {
		super(name, level);	
		setArmorDefense(10);
	}
		

	@Override
	public void upArmorLevel(Armor armor, int necessaryGold) {
		
		if (armor instanceof WarriorArmor) {
			
			WarriorArmor warriorArmor = (WarriorArmor) armor;
			
			if ( (armor.getItemLevel() - player.getLevel()) >= 1 ) {
				
				if (gold.isSufficientGoldForUp(necessaryGold)) {
					
					warriorArmor.setArmorDefense(armor.getArmorDefense() + 5);
					warriorArmor.setItemLevel(warriorArmor.getItemLevel() + 1);
				}
				
				else {
					System.err.println("Ouro insuficiente para realizar o aprimoramento de " + warriorArmor.getItemName() + " !");
				}
				
			}
		}
				
	}

	@Override
	public boolean equip(Player player) {
		
		if (isCollected()) {
			
			player.setDefense(player.getDefense() + getArmorDefense());
			
			return true;
		}
		
		return false;
	}

}
