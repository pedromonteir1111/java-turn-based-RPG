package items;

import entities.Player;

public abstract class Armor extends Item {
	
	private Player player;
	private int armorDefense;

	public Armor(String name, int level) {	
		super(name, level);		
	}

	@Override
	public boolean isCollected() {
		
		if (player.getX() == getItemX() && player.getY() == getItemY()) {
			
			if (player.getLevel() >= getItemLevel()) {
				
				setCollected(true);
				System.out.println("Você coletou " + getItemName());
				
				return true;		
			}
			
			else {
				
				/* throw new Exception("Level insuficiente para obter esse item!"); */
			}
		}
		
		return false;
	}
	
	public abstract void upArmorLevel(Armor armor, int necessaryGold);
	
	public int getArmorDefense() {
		return armorDefense;
	}

	public void setArmorDefense(int armorDefense) {
		this.armorDefense = armorDefense;
	}

}
