package items;

import entities.Player;

public class Gold extends Item {
// gerar drops aleatórios de ouro dependendo do level do player
	
	private Player player;
	private int gold;
	
	public Gold(String name, int level) { // como ouro n tem level, inicializar como -1
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
	
	public void showPlayerGold() {
		
		System.out.println(this.getGold());
	}
	
	
	public void usingGold(int amountOfGold) {
		
		if (this.getGold() >= amountOfGold) {
			
			setGold(this.getGold() - amountOfGold);			
		}
		
		else {
			
			//throw new Exception("Gold insuficiente para realizar o up!");
		}
		
		
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
}
