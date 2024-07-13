package items;

import entities.Player;
import inventory.Usable;

public class Book extends Item implements Usable {
	
	private Player player;
	private String bookContent; // a ideia é ter diferentes cores p/ representar diferentes tipos de livro, q podem contar sobre a história do reino, como lidar com os
	                            // inimigos, localização de tesouros escondidos, etc

	public Book(String name, int level, String bookContent) { // inicializar level como -1
		
		super(name, level);
		this.bookContent = bookContent;

	}

	@Override
	public boolean isCollected() {
		
		if (player.getX() == getItemX() && player.getY() == getItemY()) {
			
				setCollected(true);
				System.out.println("Você coletou " + getItemName());
				
				return true;		
		}
				
			return false;		
		
	}
	
	public void checkBookContentAndRead() {
	
		if (getBookContent().equals("Game history")) {
			readingBook();
		}
		
		else if (getBookContent().equals("Game tips")) {
			readingBook();
		}
		
		else if (getBookContent().equals("Treasure map")) {
			readingBook();
		}
			
	}
	
	public void readingBook() {
		
		
	}

	public String getBookContent() {
		return bookContent;
	}

	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}

	@Override
	public boolean use(Player player) {
		
		if (isCollected()) {
			
			checkBookContentAndRead();
			
			setCollected(false);
			
			return true;
			
		}
		
		return false;
	}
	
	

}
