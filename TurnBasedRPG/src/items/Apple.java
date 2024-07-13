package items;

import entities.Player;
import inventory.Usable;

public class Apple extends Item implements Usable {
	
	private Player player;

	public Apple(String name, int level) { // inicializar level como -1
		super(name, level);
	}

	@Override
	public boolean isCollected() {
		
		if (player.getX() == getItemX() && player.getY() == getItemY()) {
				
				setCollected(true);
				System.out.println("Você coletou " + getItemName());
				
				return true;
		}
		
		return false;	
	}
	
	public void restorePlayerHealth(Player player) {
		
		player.setHealth(player.getHealth() + 20);
	}

	@Override
	public boolean use(Player player) {
		
		if (isCollected()) {
			
			restorePlayerHealth(player);
			
			System.out.println("[MAÇÃ] Você consumiu uma maçã e recuperou 20 de vida!");
			setCollected(false);
			
			return true;
		}
		
		return false;
	}
}
