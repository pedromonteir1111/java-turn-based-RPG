package enemies;

public class Enemies {
	
	private String enemyType;
	private int enemyHealth;
	private int enemyDamage;
	
	public Enemies(String enemyType) {
		
		this.enemyType = enemyType;
		
	}
	
	
	public String getEnemyType() {
		
		return enemyType;
	}

	public int getEnemyHealth() {
		
		return enemyHealth;
	}

	public void setEnemyHealth(int enemyHealth) {
		
		this.enemyHealth = enemyHealth;
	}


	public int getEnemyDamage() {
		
		return enemyDamage;
	}

	public void setEnemyDamage(int enemyDamage) {
		
		this.enemyDamage = enemyDamage;
	}


}
