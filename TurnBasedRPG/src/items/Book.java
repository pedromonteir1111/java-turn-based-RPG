package items;

import entities.Player;

public class Book extends Item {
	
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
	
	public void checkBookContent() {
		
		if (isCollected()) {
			
			if (getBookContent().equals("Game history")) {
				System.out.println(" ");
			}
			
			else if (getBookContent().equals("Game tips")) {
				System.out.println(" ");
			}
			
			else if (getBookContent().equals("Treasure map")) {
				System.out.println(" ");
			}
		
		}
		
		setCollected(false);
	}

	public String getBookContent() {
		return bookContent;
	}

	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}
	
	

}
