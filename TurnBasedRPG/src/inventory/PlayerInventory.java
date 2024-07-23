package inventory;

import java.util.ArrayList;
import java.util.List;

import gameExceptions.InventoryFullException;
import items.Item;

public class PlayerInventory implements Inventory<Item> {
	
	private List<Item> items;
	private int inventoryCapacity;
	
	private int gold;
	
	public PlayerInventory(int inventoryCapacity) {
		
		this.items = new ArrayList<>();
		this.inventoryCapacity = inventoryCapacity;
		
		setGold(50);
	}

	@Override
	public void addItem(Item item) throws InventoryFullException {
		
		if (this.items.size() < getInventoryCapacity()) {
			
			this.items.add(item);
			item.setCollected(true);
			
			System.out.println("[ITEM] O item " + item.getItemName() + " foi adicionado ao seu inventário.");
		}
		
		else {
			throw new InventoryFullException("Seu inventário está cheio!");
		}
		
	}
	
	@Override
    public void selectItem(Item item) {
        if (items.contains(item)) {
            System.out.println("Item selecionado: " + item.getItemName());
        }
    }

	@Override
	public Item findItem(String name) {
		
		for (Item item : items) {
			
			if (item.getItemName().equals(name)) {
				return item;
			}		
		}
		
		return null;
	}
	
	@Override
	public void removeItem(Item item) {
		
		if (items.remove(item)) {
			
			item.setCollected(false);
			
			System.out.println("O item " + item.getItemName() + " foi removido do seu inventário.");		
		}	
		
	}

	@Override
	public List<Item> getItems() {
		return new ArrayList<>(items);
	}
	
	public int getInventoryCapacity() {
		return inventoryCapacity;
	}

	public void setInventoryCapacity(int inventoryCapacity) {
		this.inventoryCapacity = inventoryCapacity;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

}
