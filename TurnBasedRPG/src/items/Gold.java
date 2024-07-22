package items;

import entities.Player;
import inventory.PlayerInventory;

public class Gold extends Item {
// gerar drops aleatórios de ouro dependendo do level do player
	
	private Player player;
	private PlayerInventory playerInventory;
	
	public Gold(String name, int level) { // como ouro não tem level, inicializar como -1
		super(name, level);
	}

	@Override
	public boolean isCollected() {
		
		if (player.getX() == getItemX() && player.getY() == getItemY()) {
			
				setCollected(true);
				System.out.println("Você coletou " + getItemName());
				
				addCollectedGold();
				
				return true;		
			
		}		
			return false;		
	}
	
	private void addCollectedGold() {
		
		playerInventory.setGold(playerInventory.getGold() + 50);
		
	}

	public void showPlayerGold() {
		
		System.out.println(playerInventory.getGold());
	}
	
	
	public boolean isSufficientGoldForUp(int necessaryGold) {
		
		if (playerInventory.getGold() >= necessaryGold) {
			
			playerInventory.setGold(playerInventory.getGold() - necessaryGold);	
			
			return true;
		}
		
		else {
			
			//throw new Exception("Gold insuficiente para realizar o up!");
		}
		
		return false;
		
	}

}
