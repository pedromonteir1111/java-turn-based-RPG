package items;

import entities.Entity;

public abstract class Weapon extends Item {
	  
	  private Entity player;
	  private int weaponDamage; 
	  
	  public Weapon(String name, int level) { 
		  
		  super(name, level);
	  }
	  
	  
	  public int getWeaponDamage() {
		  return weaponDamage; 
	  }
	  
	  public void setWeaponDamage(int weaponDamage) {
		  this.weaponDamage = weaponDamage; 
	  }
	   
	@Override
	public boolean isCollected() {
		
		if (player.getX() == getItemX() && player.getY() == getItemY()) {
			
			if (player.getLevel() >= getItemLevel()) {
				
				setCollected(true);
				System.out.println("Você coletou " + getItemName());
				
				return true;		
			}
			
			else {
				
				/* throw new Exception("Level insuficiente para obter esse item!"); */
			}
		}
		
		return false;
	}
	
	public abstract void upWeaponLevel(Weapon weapon); 
  
  }
