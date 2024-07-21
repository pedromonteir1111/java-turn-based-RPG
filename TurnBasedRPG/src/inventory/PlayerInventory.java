package inventory;

import java.util.ArrayList;
import java.util.List;
import items.Item;
import items.WarriorArmor;

public class PlayerInventory implements Inventory<Item> {
    
    private List<Item> items;
    private int inventoryCapacity;
    
    public PlayerInventory(int inventoryCapacity) {
    	
        this.items = new ArrayList<>();
        this.inventoryCapacity = inventoryCapacity;
        
        initializeInventory();
    }
    
    private void initializeInventory() {
    	
        WarriorArmor initialArmor = new WarriorArmor("Armadura de Prata", 1);
        addItem(initialArmor);
    }

    @Override
    public void addItem(Item item) {
    	
        if (this.items.size() < getInventoryCapacity()) {
        	
            this.items.add(item);
            item.setCollected(true);
            
            setInventoryCapacity(getInventoryCapacity() - 1);
            
            System.out.println("[ITEM] O item " + item.getItemName() + " foi adicionado ao seu inventário.");
            
        }
        
        else {
            System.out.println("Seu inventário está cheio!");
        }
    }

    @Override
    public void selectItem(Item item) {
        if (items.contains(item)) {
            System.out.println("Item selecionado: " + item.getItemName());
        }
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
}
