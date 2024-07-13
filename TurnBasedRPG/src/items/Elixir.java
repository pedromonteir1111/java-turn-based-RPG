package items;

import entities.Player;
import gamestates.Gamestate;

// ao coletar, aumenta 20 de ataque no próximo combate do player. o efeito acaba quando o combate é finalizado

public class Elixir extends Item {
	
	private Player player;

	public Elixir(String name, int level) { // inicializar level como -1
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
	
	public void usingElixir(Player player) {
		player.setAttack(player.getAttack() + 20);			
	}
	
	public void removingElixirEffect(Player player) {
		player.setAttack(player.getAttack() - 20);
	}

}
