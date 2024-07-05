package combat;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;

import entities.Archer;
import entities.Enemy;
import entities.Mage;
import entities.Player;
import entities.Rogue;
import entities.Warrior;
import game.GamePanel;
import game.ScreenSettings;
import gamestates.Gamestate;
import userInputs.Inputs;

public class CombatSystem {
	
	private ScreenSettings screenSettings;
	private Square[][] squares;
	private Stack<Square> selectedSquares = new Stack<Square>();
	private Square lastSelectedSquare;
	private Square hoveredSquare;
	private Square lastHoveredSquare;
	private String lastInput;
	private int tileSize;
	private int turns;
	private boolean isPlayerAction;
	private GamePanel gamePanel;
	
	private Player[] allies;
	private Enemy[] enemies;
	private ArrayList<Player> liveAllies = new ArrayList<Player>();
	private ArrayList<Enemy> liveEnemies = new ArrayList<Enemy>();
	private Player selectedPlayer;
	
	public CombatSystem(Player player, ScreenSettings screenSettings, GamePanel gamePanel) {
		
		this.allies = new Player[4];
		this.enemies = new Enemy[4];
		this.allies[0] = player;
		this.tileSize = screenSettings.getTileSize();
		this.screenSettings = screenSettings;
		this.gamePanel = gamePanel;
		this.turns = 0;
		
		this.squares = new Square[16][7];
		
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 7; j++) {
				squares[i][j] = new Square(i * tileSize + 2 * tileSize, j * tileSize + tileSize, i, j);
			}
		}
		
		this.lastSelectedSquare = squares[0][0];
		this.lastHoveredSquare = squares[0][0];
	}
	
	public void runCombat(int mouseX, int mouseY, Inputs input) {
		
		if (turns == 0) {
			initCombat();
			//showRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), 3);
		}
		
		for(Player ally : liveAllies) {
			squares[ally.getSquareX()][ally.getSquareY()].setCurrentPlayer(ally);
		}
	
		switch (CombatStates.state) {
		
		case WAITING_INPUT:
			
			if (mouseX >= 0) {
				hoveredSquare = getSqr(mouseX, mouseY);
			}
			switch(input) {
			
			case CLICK:
				
				Square clickedSquare = getSqr(mouseX, mouseY);	
				
				if(selectedSquares.search(clickedSquare) == -1 && clickedSquare.getCurrentPlayer() == null) {
					
					if(!selectedSquares.isEmpty()) {
						removeSelection();
					}
					
					selectedSquares.push(clickedSquare);
					
				} else {
					selectedSquares.removeElement(clickedSquare);
				}		
				
				break;	
			
			
			case SPACE:
				
				if (!selectedSquares.empty()) {
					if (getManhattanDistance(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(),
							selectedSquares.peek().getRelativeX(),
							selectedSquares.peek().getRelativeY()) <= selectedPlayer.getWalkRange()) {

						CombatStates.state = CombatStates.ACTION_WALK;
						runCombat(mouseX, mouseY, input);

					} else {
						java.awt.Toolkit.getDefaultToolkit().beep();
					} 
				}
				break;
				
			case W:
				break;
				
			case E:
				break;
				
			case Q:
				break;
				
			case NONE:
				
				break;
			
			}
			
			break;
			
		case ACTION_WALK:
			
			walk();
			turns++;
			
			break;

		default:
		
			break;
		}
		
		setSqrImages();
		
		gamePanel.repaint();
		gamePanel.revalidate();
		
		System.out.println(turns);
	}
	
	//incompleto
	private void showRange(int originX, int originY, int range) {
		
		for(int i = originY - range; i < originY + range - 1; i++) {
			for(int j = originX - range; j < originY + range - 1; j++) {
				
				squares[j][i].setImage("Range");
			}
		}	
	}

	private void initCombat() {
		
		for(Player ally : allies) {
			if(ally != null) {				
				liveAllies.add(ally);
			}
		}
		
		liveAllies.get(0).setSquareX(4);
		liveAllies.get(0).setSquareY(3);
		
		liveAllies.get(0).setX(squares[liveAllies.get(0).getSquareX()][liveAllies.get(0).getSquareY()].getX());
		liveAllies.get(0).setY(squares[liveAllies.get(0).getSquareX()][liveAllies.get(0).getSquareY()].getY());
		
		CombatStates.state = CombatStates.WAITING_INPUT;
		selectedPlayer = liveAllies.get(0);
		turns++;
	}
	
	public void leaveCombat() {
		
		for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 16; col++) {
				
				squares[col][row].emptySquare();
				squares[col][row].setImage("");
			}		
		}
		
		Gamestate.state = Gamestate.PLAYING;
		this.turns = 0;
		removeSelection();
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
	
	private int getManhattanDistance(int x1, int y1, int x2, int y2) {
		
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
		
	}
	
	private void walk() {
		
		squares[selectedPlayer.getSquareX()][selectedPlayer.getSquareY()].emptySquare();
		squares[selectedPlayer.getSquareX()][selectedPlayer.getSquareY()].setImage("");;
		
		selectedPlayer.setSquareX(selectedSquares.peek().getRelativeX());
		selectedPlayer.setSquareY(selectedSquares.peek().getRelativeY());

		new Move(selectedPlayer, gamePanel, selectedSquares.peek());
		
		removeSelection();
		
		CombatStates.state = CombatStates.WAITING_INPUT;
		
		
	}
	
	private Square getSqr(int x, int y) {
		
		return squares[Math.min(15, Math.max(0, (x - tileSize * 2)/tileSize))][Math.min(6, Math.max(0, (y - tileSize)/tileSize))];
	}
	
	private void setSqrImages() {
		
		if (lastHoveredSquare != null) {
			lastHoveredSquare.setImage("");
		}
		
		if (hoveredSquare != null) {
			hoveredSquare.setImage("Hover");
		}
		
		if(selectedPlayer instanceof Warrior) {
			squares[selectedPlayer.getSquareX()][selectedPlayer.getSquareY()].setImage("Warrior");
			
		} else if(selectedPlayer instanceof Mage) {
			squares[selectedPlayer.getSquareX()][selectedPlayer.getSquareY()].setImage("Mage");
			
		} else if(selectedPlayer instanceof Archer) {
			squares[selectedPlayer.getSquareX()][selectedPlayer.getSquareY()].setImage("Archer");
			
		} else if(selectedPlayer instanceof Rogue) {
			squares[selectedPlayer.getSquareX()][selectedPlayer.getSquareY()].setImage("Rogue");
		}
		
		
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
