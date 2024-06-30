package entities;

public abstract class Entity {
	
	private int X;
	private int Y;
	private int squareX;
	private int squareY;
	private int health;
	private int attack;
	private int defense;
	private int speed;
	private int walkRange;
	
	public abstract void attackQ();
	
	public abstract void attackW();
	
	public abstract void attackE();

	public int getHealth() { return health; }

	public void setHealth(int health) { this.health = health; }

	public int getAttack() { return attack; }

	public void setAttack(int attack) { this.attack = attack; }

	public int getDefense() { return defense; }

	public void setDefense(int defense) { this.defense = defense; }

	public int getSpeed() { return speed; }

	public void setSpeed(int speed) { this.speed = speed; }

	public int getX() { return X; }

	public void setX(int X) { this.X = X; }

	public int getY() { return Y; }

	public void setY(int Y) { this.Y = Y; }

	public int getSquareX() { return squareX; }

	public void setSquareX(int X) { this.X = X; }

	public int getSquareY() { return squareY; }

	public void setSquareY(int Y) { this.Y = Y; }

	public int getWalkRange() { return walkRange; }

	public void setWalkRange(int walkRange) { this.walkRange = walkRange; }
}
