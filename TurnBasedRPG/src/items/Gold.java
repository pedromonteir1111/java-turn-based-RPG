package items;

import entities.Player;

public class Gold extends Item {
// gerar drops aleatórios de ouro dependendo do level do player
	
	private Player player;
	
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
		
		player.setGold(player.getGold() + 50);
		
	}

	public void showPlayerGold() {
		
		System.out.println(player.getGold());
	}
	
	
	public boolean isSufficientGoldForUp(int necessaryGold) {
		
		if (player.getGold() >= necessaryGold) {
			
			player.setGold(player.getGold() - necessaryGold);	
			
			return true;
		}
		
		else {
			
			//throw new Exception("Gold insuficiente para realizar o up!");
		}
		
		return false;
		
	}

}
