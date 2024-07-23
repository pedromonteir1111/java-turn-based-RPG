package map;

public class Room {

	private String filePath;
	private boolean willTriggerCombat, hasTopDoor, hasBottomDoor, hasRightDoor, hasLeftDoor, wasVisited;
	private int[] enemies;
	
	public Room(String path, boolean combat, int[] enemies, boolean[] doors){
		this.filePath = path;
		this.willTriggerCombat = combat;
		this.enemies = enemies;
		
		this.hasTopDoor = doors[0];
		this.hasBottomDoor = doors[1];
		this.hasLeftDoor = doors[2];
		this.hasRightDoor = doors[3];
		
		this.wasVisited = false;
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

	public boolean hasTopDoor() {
		return hasTopDoor;
	}

	public boolean hasBottomDoor() {
		return hasBottomDoor;
	}

	public boolean hasRightDoor() {
		return hasRightDoor;
	}

	public boolean hasLeftDoor() {
		return hasLeftDoor;
	}

	public boolean wasVisited() {
		return wasVisited;
	}

	public void setWasVisited(boolean wasVisited) {
		this.wasVisited = wasVisited;
	}
	
}
