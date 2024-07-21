package entities;

import game.ScreenSettings;

public abstract class Enemy extends Entity {
	
	private int attackRange;
	
	public Enemy (ScreenSettings screenSettings, String filePath) {
		super(screenSettings, filePath);
	}

	public int getAttackRange() {
		return attackRange;
	}

	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}
}
