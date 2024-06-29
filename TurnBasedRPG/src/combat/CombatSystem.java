package combat;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;

import enemies.Enemy;
import game.GamePanel;
import game.ScreenSettings;
import playerClasses.Player;

public class CombatSystem {
	
	private ScreenSettings screenSettings;
	private Square[][] squares;
	private Stack<Square> selectedSquares = new Stack<Square>();
	private Square lastSelectedSquare;
	private Square hoveredSquare;
	private Square lastHoveredSquare;
	private int tileSize;
	private int turns;
	private boolean isPlayerAction;
	private GamePanel gamePanel;
	
	private ArrayList<Player> allies = new ArrayList<Player>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private Player selectedPlayer;
	
	public CombatSystem(Player player, ScreenSettings screenSettings, GamePanel gamePanel) {
		
		this.allies.add(player);
		this.tileSize = screenSettings.getTileSize();
		this.screenSettings = screenSettings;
		this.gamePanel = gamePanel;
		
		this.allies.get(0).setSquareX(4);
		this.allies.get(0).setSquareY(3);
		
		this.squares = new Square[16][7];
		
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 7; j++) {
				squares[i][j] = new Square(i * tileSize + 2 * tileSize, j * tileSize + tileSize, i, j);
			}
		}
		
		this.lastSelectedSquare = squares[0][0];
		this.lastHoveredSquare = squares[0][0];
	}
	
	public void runCombat(int mouseX, int mouseY, boolean mousePressed) {
		
		if (turns == 0) {
			allies.get(0).setPlayerX(squares[allies.get(0).getSquareX()][allies.get(0).getSquareY()].getX());
			allies.get(0).setPlayerY(squares[allies.get(0).getSquareX()][allies.get(0).getSquareY()].getY());
		}
		for(Player ally : allies) {
			squares[ally.getSquareX()][ally.getSquareY()].setCurrentPlayer(ally);
		}
		
		hoveredSquare = getSqr(mouseX, mouseY);
	
		if(mousePressed) {
			
			Square clickedSquare = getSqr(mouseX, mouseY);
			
			if(selectedSquares.search(clickedSquare) == -1) {
				if(lastSelectedSquare.getCurrentPlayer() == null) {
					removeSelection();	
				}
				
				selectedSquares.push(clickedSquare);
			} else {
				selectedSquares.removeElement(clickedSquare);
			}
			
			if(isPlayerAction && clickedSquare.getCurrentPlayer() == null) {
				selectedPlayer.setPlayerX(clickedSquare.getX());
				selectedPlayer.setPlayerY(clickedSquare.getY());
				
				selectedPlayer.setSquareX(clickedSquare.getRelativeX());
				selectedPlayer.setSquareY(clickedSquare.getRelativeY());
				
				removeSelection();
				
				turns++;
				isPlayerAction = false;
			}
			
			if (!selectedSquares.empty()) {
				
				lastSelectedSquare = selectedSquares.peek();
				
				if(clickedSquare.getCurrentPlayer() == selectedPlayer && clickedSquare.getCurrentPlayer() != null) {
					removeSelection();
					selectedPlayer = null;
				} else {
					if(clickedSquare.getCurrentPlayer() != null) {
						selectedPlayer = clickedSquare.getCurrentPlayer();
						isPlayerAction = true;
					}
				}	
			}
		}
		
		setSqrImages();
		
		gamePanel.repaint();
		gamePanel.revalidate();
	}
	
	public void drawGrid(Graphics2D g2D) {
		
		int x = 0, y = 0;
		
		x = tileSize * 2;
		y = tileSize;

		for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 16; col++) {
				g2D.drawImage(squares[col][row].getImage(), x, y, tileSize, tileSize, null);
				x += tileSize;
			}
				
			x = tileSize * 2;
			y += tileSize;
		}
	}
	
	private Square getSqr(int mouseX, int mouseY) {
		
		return squares[Math.min(15, Math.max(0, (mouseX - tileSize * 2)/tileSize))][Math.min(6, Math.max(0, (mouseY - tileSize)/tileSize))];
	}
	
	private void setSqrImages() {
		
		lastHoveredSquare.setImage("");
		
		hoveredSquare.setImage("Hover");
		
		for(Square square : selectedSquares) {
			square.setImage("Selected");
		}
		
		if(hoveredSquare != lastHoveredSquare) {
			lastHoveredSquare = hoveredSquare;
		}
	}
	
	private void removeSelection() {
		for(Square square : selectedSquares) {
			square.setImage("");
		}
		
		selectedSquares.removeAllElements();
	}
}
