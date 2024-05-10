package playerClasses;

public class Player {
	
	private String playerClass;
	private int health;
	private int attack;
	private int defense;
	private int mana;
	private int agility; // quanto maior a agilidade, maior a chance de acertar o ataque
	private boolean dead;
	
	public Player(String playerClass) {
		
		this.playerClass = playerClass;
		
	}
	
	
	public String getPlayerClass() {
		
		return playerClass;
	}
	
	public int getHealth() {
		
		return health;
	}
	
	public void setHealth(int health) {
		
		this.health = health;
	}
	
	public int getAttack() {
		
		return attack;
	}
	
	public void setAttack(int attack) {
		
		this.attack = attack;
	}
	
	public int getDefense() {
		
		return defense;
	}
	
	public void setDefense(int defense) {
		
		this.defense = defense;
	}
	
	public int getMana() {
		
		return mana;
	}
	
	public void setMana(int mana) {
		
		this.mana = mana;
	}
	
	public int getAgility() {
		
		return agility;
	}
	
	public void setAgility(int agility) {
		
		this.agility = agility;
	}
	
	public boolean isDead() {
		
		return dead;
	}
	
	public void setDead(boolean dead) {
		
		this.dead = dead;
	}
	
	
}
