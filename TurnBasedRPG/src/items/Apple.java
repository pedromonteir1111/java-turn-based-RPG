package items;

import entities.Player;

public class Apple extends Item {
	
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
		
		if (isCollected()) {
			
			player.setHealth(player.getHealth() + 20);
			
			setCollected(false);		
		}
	}
}
