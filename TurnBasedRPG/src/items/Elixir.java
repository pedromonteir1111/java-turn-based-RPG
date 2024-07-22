package items;

import entities.Player;
import gamestates.Gamestate;
import inventory.PlayerInventory;
import inventory.Usable;

// ao coletar, aumenta 20 de ataque no próximo combate do player. o efeito acaba quando o combate é finalizado

public class Elixir extends Item implements Usable {
	
	private Player player;
	private PlayerInventory playerInventory;
	private boolean used;
	
	public Elixir(String name, int level) { // inicializar level como 0
		super(name, level);
		
	}
	
	@Override
	public boolean isCollected() {
		
		/*
		 * if (player.getX() == getItemX() && player.getY() == getItemY()) {
		 * 
		 * setCollected(true); System.out.println("Você coletou " + getItemName());
		 * 
		 * return true; }
		 */
		if ((Elixir) playerInventory.findItem("Elixir Milagroso") != null) {
			setCollected(true);
		}
		
		return false;	
	}
	
	public void usingElixir(Player player) {
		player.setAttack(player.getAttack() + 20);			
	}
	
	public void removingElixirEffect(Player player) {
		
		player.setAttack(player.getAttack() - 20);
		used = false;
	}

	@Override
	public boolean use(Player player) {
		
		if (isCollected()) {
			
			usingElixir(player);
			System.out.println("[ELIXIR] Você ganhou 20 de poder de ataque nesse combate!");
			
			setCollected(false);
			
			used = true;
			
			return true;
		}
		
		return false;	
	}
	
	public boolean isUsed() {
		return used;
	}
		
}
