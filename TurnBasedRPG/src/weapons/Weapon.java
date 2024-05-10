package weapons;

public class Weapon {
	
	private int weaponDamage;
	private String weaponType;
	
	public Weapon(String weaponType) {
		
		this.weaponType = weaponType;
	}
	
	
	public int getWeaponDamage() {
		
		return weaponDamage;
	}
	
	public void setWeaponDamage(int weaponDamage) {
		
		this.weaponDamage = weaponDamage;
	}
	
	public String getWeaponType() {
		
		return weaponType;
	}
	
	public void setWeaponType(String weaponType) {
		
		this.weaponType = weaponType;
	}
	
	

}
