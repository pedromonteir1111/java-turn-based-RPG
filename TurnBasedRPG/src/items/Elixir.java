package items;

import entities.Player;
import gamestates.Gamestate;
import inventory.PlayerInventory;
import inventory.Usable;

// quando coletado, aumenta entre 15-30 de ataque no próximo combate do player. o efeito acaba quando o combate é finalizado

public class Elixir extends Item implements Usable {
	
	private Player player;
	private PlayerInventory playerInventory;
	private boolean used = false;
	
	private int elixirAttackBoost;

	public Elixir(String name, int level, PlayerInventory playerInventory) { // inicializar level como 0
		
		super(name, level);
		this.playerInventory = playerInventory;	
		
		setElixirAttackBoost(elixirAttackBoost);
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
		
			if (playerInventory.findItem("Elixir Milagroso") != null) {
				
				return true;
			}
		
		return false;	
	}
	
	public void usingElixir(Player player) {
		
		player.setAttack(player.getAttack() + getElixirAttackBoost());			
	}
	
	public void removingElixirEffect(Player player) {
		
		player.setAttack(player.getAttack() - getElixirAttackBoost());
		used = false;
	}

	@Override
	public boolean use(Player player) {
		
		if (isCollected() && !used) {
			
			usingElixir(player);
			System.out.println("[ELIXIR] Você ganhou " + elixirAttackBoost + " de poder de ataque nesse combate!");
			
			setCollected(false);
			
			used = true;
			
			return true;
		}
		
		return false;	
	}
	
	public boolean isUsed() {
		return used;
	}
	
	public int getElixirAttackBoost() {
		return elixirAttackBoost;
	}

	public void setElixirAttackBoost(int elixirAttackBoost) {
		this.elixirAttackBoost = (int)(Math.random() * 15 + 15); // gerador de boost entre 15-30 de ataque quando utilizado o elixir
	}
		
}
