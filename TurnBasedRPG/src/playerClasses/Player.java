package playerClasses;

public class Player {
	
	private String playerClass;
	private int playerX;
	private int playerY;
	private int health;
	private int attack;
	private int defense;
	private int mana;
	private int agility; // quanto maior a agilidade, maior a chance de acertar o ataque
	private int speed;
	private boolean dead;
	private boolean inCombat;
	
	public Player(String playerClass) {
		
		this.playerClass = playerClass;
		inCombat = true;
		
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


	public int getSpeed() {
		
		return speed;
	}


	public void setSpeed(int speed) {
		
		this.speed = speed;
	}


	public int getPlayerX() {
		
		return playerX;
	}


	public void setPlayerX(int playerX) {
		
		this.playerX = playerX;
	}


	public int getPlayerY() {
		
		return playerY;
	}


	public void setPlayerY(int playerY) {
		
		this.playerY = playerY;
	}
	
	public boolean isInCombat() {
		return inCombat;
	}
	
	public void setInCombat(boolean inCombat) {
		this.inCombat = inCombat;
	}
	
}
