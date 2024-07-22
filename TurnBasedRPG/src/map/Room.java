package map;

public class Room {

	private String filePath;
	private boolean willTriggerCombat;
	private int[] enemies;
	
	public Room(String path, boolean combat, int[] enemies){
		this.filePath = path;
		this.willTriggerCombat = combat;
		this.enemies = enemies;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean willTriggerCombat() {
		return willTriggerCombat;
	}

	public int[] getEnemies() {
		return enemies;
	}
	
}
