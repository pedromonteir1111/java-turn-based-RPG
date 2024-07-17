package items;


public abstract class Item {
	
	private String itemName;
	private int itemLevel;
	private int itemX;
	private int itemY;
	private int relativeX;
	private int relativeY;
	private boolean collected;
	
	public Item(String name, int level) {
		
		this.itemName = name;
		this.itemLevel = level;
		this.collected = false;
	}

	public abstract boolean isCollected();
	
	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	public String getItemName() {
		return itemName;
	}

	public int getItemLevel() {
		return itemLevel;
	}
	
	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}

	public int getItemX() {
		return itemX;
	}

	public void setItemX(int itemX) {
		this.itemX = itemX;
	}

	public int getItemY() {
		return itemY;
	}

	public void setItemY(int itemY) {
		this.itemY = itemY;
	}

	public int getRelativeX() {
		return relativeX;
	}

	public void setRelativeX(int relativeX) {
		this.relativeX = relativeX;
	}

	public int getRelativeY() {
		return relativeY;
	}

	public void setRelativeY(int relativeY) {
		this.relativeY = relativeY;
	}


}
