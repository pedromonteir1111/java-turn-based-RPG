package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import inventory.PlayerInventory;
import items.Item;

public class InventoryUI {
    
    private PlayerInventory playerInventory;
    
    private boolean isVisible = false;
    
    private int inventoryX, inventoryY;
    private int inventoryWidth, inventoryHeight;
    
    private List<Rectangle> itemRectangles;
    
    public InventoryUI(PlayerInventory playerInventory, int screenWidth, int screenHeight) {
        
        this.playerInventory = playerInventory;
        
        this.inventoryWidth = screenWidth / 3;
        this.inventoryHeight = screenHeight / 2;
        
        this.inventoryX = (screenWidth - inventoryWidth) / 2;
        this.inventoryY = (screenHeight - inventoryHeight) / 2;
        
        this.itemRectangles = new ArrayList<>();
    }
    
    public void toggleInventoryVisibility() {
        isVisible = !isVisible;
    }
    
    public boolean isVisible() {
        return isVisible;
    }
    
    public void draw(Graphics2D g2D) {
        
        if (!isVisible) {
            return;
        }
        
        // desenhando e preenchendo o retângulo da UI
        g2D.setColor(new Color(0, 0, 0, 150));
        g2D.fillRect(inventoryX, inventoryY, inventoryWidth, inventoryHeight);
        g2D.setColor(Color.CYAN);
        g2D.drawRect(inventoryX, inventoryY, inventoryWidth, inventoryHeight);
        
        // cabeçalho da UI
        Font headerFont = new Font("Arial", Font.BOLD, 18);
        g2D.setFont(headerFont);
        
        FontMetrics headerMetrics = g2D.getFontMetrics(headerFont);
        String headerText = "Inventário";
        
        int headerX = inventoryX + (inventoryWidth - headerMetrics.stringWidth(headerText)) / 2;
        int headerY = inventoryY + headerMetrics.getAscent() + 10;
        g2D.drawString(headerText, headerX, headerY);
        

        // lista de itens do playerInventory

        List<Item> items = playerInventory.getItems();
        
        g2D.setColor(Color.GREEN);
        itemRectangles.clear();
        
        Font font = new Font("Arial", Font.BOLD, 16);
        g2D.setFont(font);
        FontMetrics metrics = g2D.getFontMetrics(font);
        
        int itemY = headerY + 50; 
        
        // exibindo um item e seu nível atual

        for (Item item : items) {
        	
            String itemDescription = item.getItemName() + " ~~~> Level: " + item.getItemLevel();
            
            int textWidth = metrics.stringWidth(itemDescription);
            int textHeight = metrics.getHeight();  

            Rectangle itemRect = new Rectangle(inventoryX + 10, itemY - textHeight, Math.max(textWidth, inventoryWidth - 20), textHeight);
            itemRectangles.add(itemRect);
            
            int drawY = itemY + textHeight / 2;
            
            g2D.drawString(itemDescription, inventoryX + 10, drawY);
            
            itemY += textHeight + 20;
        }
        
        // gerenciamento do ouro
        g2D.setColor(Color.YELLOW);
        
        int goldAmount = playerInventory.getGold();
        String goldText = "Ouro: " + goldAmount;
        
        int goldTextWidth = metrics.stringWidth(goldText);
        int goldX = inventoryX + (inventoryWidth - goldTextWidth) / 2;
        int goldY = inventoryY + inventoryHeight - 20;
        
        g2D.drawString(goldText, goldX, goldY);
     

    }
    
    // essa parte está instável, mas, no geral, remover um item com dois cliques no seu nome funciona
    public void handleMouseClick(Point point) {
    	
        if (!isVisible) {
            return;
        }
        
        for (int i = 0; i < itemRectangles.size(); i++) {
        	
            if (itemRectangles.get(i).contains(point)) {
            	
                Item selectedItem = playerInventory.getItems().get(i);
                playerInventory.removeItem(selectedItem);
                
                break;
            }
        }
    }
}
