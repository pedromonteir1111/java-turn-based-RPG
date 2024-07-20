package combat;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import entities.Archer;
import entities.Enemy;
import entities.Goblin;
import entities.Mage;
import entities.Player;
import entities.Rogue;
import entities.Warrior;
import game.GamePanel;
import game.ScreenSettings;
import gamestates.Gamestate;
import items.Elixir;
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
	private GamePanel gamePanel;
	private Random random = new Random();
	private Player[] allies;
	private Enemy[] enemies;
	private ArrayList<Player> liveAllies = new ArrayList<Player>();
	private ArrayList<Enemy> liveEnemies = new ArrayList<Enemy>();
	private Player selectedPlayer;
	private int playerIndex;
	int[] attackCoords;

	private Elixir elixir;

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

		// System.out.println(turns);

		if (turns == 0) {
			initCombat();
			// showRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), 3);
		}

		for (Player ally : liveAllies) {
			squares[ally.getSquareX()][ally.getSquareY()].setCurrentPlayer(ally);
		}

		for (Enemy enemy : liveEnemies) {
			squares[enemy.getSquareX()][enemy.getSquareY()].setCurrentEnemy(enemy);
		}

		if (mouseX >= 0) {
			hoveredSquare = getSqr(mouseX, mouseY);
		}

		switch (CombatStates.state) {

		case WAITING_INPUT:

			switch (input) {

			case CLICK:

				Square clickedSquare = getSqr(mouseX, mouseY);

				if (selectedSquares.search(clickedSquare) == -1 && clickedSquare.getCurrentPlayer() == null && clickedSquare.getCurrentEnemy() == null) {

					if (!selectedSquares.isEmpty()) {
						removeSelection();
					}

					if (getManhattanDistance(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(),
							clickedSquare.getRelativeX(),
							clickedSquare.getRelativeY()) <= selectedPlayer.getWalkRange()) {

						selectedSquares.push(clickedSquare);
					}

				} else {
					selectedSquares.removeElement(clickedSquare);
				}

				break;

			case SPACE:

				if (!selectedSquares.empty()) {

					CombatStates.state = CombatStates.ACTION_WALK;
					runCombat(mouseX, mouseY, input);
				}

				break;

			case Q:

				removeRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
				removeSelection();
				CombatStates.state = CombatStates.SELECT_ATTACK_LOCATION;

				break;

			case NONE:

				break;

			default:
				break;

			}

			break;

		case ACTION_WALK:

			removeRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
			walk();
			changeCharacter();

			break;

		case SELECT_ATTACK_LOCATION:

			int[] directions = getAttackDirection(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(),
					hoveredSquare.getRelativeX(), hoveredSquare.getRelativeY());

			attackCoords = selectedPlayer.attack(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(),
					directions[0], directions[1]);

			switch (input) {

			case CLICK:

				Square clickedSquare = getSqr(mouseX, mouseY);

				if (selectedSquares.search(clickedSquare) == -1) {

					if (!selectedSquares.isEmpty()) {
						removeSelection();
					}

					for (int i = 0; i < attackCoords.length; i += 2) {
						if (attackCoords[i] < 16 && attackCoords[i] >= 0 && attackCoords[i + 1] < 7
								&& attackCoords[i + 1] >= 0) {
							selectedSquares.push(squares[attackCoords[i]][attackCoords[i + 1]]);
						}
					}

				} else {
					removeSelection();
				}

				break;

			case ESC:

				removeSelection();
				showRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
				CombatStates.state = CombatStates.WAITING_INPUT;
				break;

			case SPACE:

				if (!selectedSquares.empty()) {

					CombatStates.state = CombatStates.ACTION_ATTACK;
					runCombat(mouseX, mouseY, input);
				}

				break;

			default:
				break;
			}

			break;

		case ACTION_ATTACK:

			attack();
			removeSelection();
			changeCharacter();

			break;

		default:

			break;
		}

		setSqrImages();

		gamePanel.repaint();
		gamePanel.revalidate();

	}

	private void showRange(int originX, int originY, int range) {

		for (int i = -range; i <= range; i++) {
			for (int j = -range; j <= range; j++) {

				if (Math.abs(i) + Math.abs(j) <= range && i + originX >= 0 && i + originX < 16 && j + originY >= 0
						&& j + originY < 7) {
					squares[i + originX][j + originY].setImage("Range");
					squares[i + originX][j + originY].setInWalkRange(true);
				}
			}
		}
	}

	private void removeRange(int originX, int originY, int range) {

		for (int i = -range; i <= range; i++) {
			for (int j = -range; j <= range; j++) {

				if (Math.abs(i) + Math.abs(j) <= range && i + originX >= 0 && i + originX < 16 && j + originY >= 0
						&& j + originY < 7) {
					squares[i + originX][j + originY].setImage("");
					squares[i + originX][j + originY].setInWalkRange(false);
				}
			}
		}
	}

	private void changeCharacter() {

		playerIndex++;

		if (playerIndex > liveAllies.size() - 1) {
			playerIndex = 0;
			turns++;
		}

		selectedPlayer = liveAllies.get(playerIndex);

		CombatStates.state = CombatStates.WAITING_INPUT;
		showRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
	}

	private void removeElixirIfUsed() {

		if (elixir != null && elixir.use(selectedPlayer)) {
			elixir.removingElixirEffect(selectedPlayer);
		}
	}

	private void initCombat() {

		for (Player ally : allies) {
			if (ally != null) {
				liveAllies.add(ally);
			}
		}

//		elixir.use(selectedPlayer);

		for (Player ally : liveAllies) {

			int x, y;

			do {

				x = random.nextInt(7);
				y = random.nextInt(7);

			} while (squares[x][y].getCurrentPlayer() != null || squares[x][y].getCurrentEnemy() != null);

			ally.setSquareX(x);
			ally.setSquareY(y);

			ally.setX(squares[x][y].getX());
			ally.setY(squares[x][y].getY());
		}

		for (Enemy enemy : liveEnemies) {

			int x, y;

			do {

				x = random.nextInt(9, 16);
				y = random.nextInt(7);

			} while (squares[x][y].getCurrentPlayer() != null || squares[x][y].getCurrentEnemy() != null);

			enemy.setSquareX(x);
			enemy.setSquareY(y);

			enemy.setX(squares[x][y].getX());
			enemy.setY(squares[x][y].getY());
		}

		CombatStates.state = CombatStates.WAITING_INPUT;
		selectedPlayer = liveAllies.get(0);
		turns++;
		playerIndex = 0;

		showRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
	}

	public void leaveCombat() {

		for (int row = 0; row < 7; row++) {
			for (int col = 0; col < 16; col++) {

				squares[col][row].emptySquare();
				squares[col][row].setImage("");
			}
		}

//		removeElixirIfUsed();

		Gamestate.state = Gamestate.PLAYING;
		this.turns = 0;
		removeSelection();
		removeRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
		System.out.println(liveEnemies);
		liveAllies.clear();
		liveEnemies.clear();
	}

	public void createEnemies(int[] enemyTypes) {
		// 0 eh goblin, 1 slime e 2 skeleton

		for (int i = 0; i < enemyTypes.length; i++) {
			switch (enemyTypes[i]) {
			case 0:
				liveEnemies.add(new Goblin(screenSettings));
				break;

			case 1:

				break;

			case 2:

				break;

			default:
				break;
			}
		}
	}

	public void drawEnemies(Graphics2D g2D) {
		for (Enemy enemy : liveEnemies) {
			enemy.draw(g2D);
		}
	}

	public void drawGrid(Graphics2D g2D) {

		int x = 0, y = 0;

		x = tileSize * 2;
		y = tileSize;

		for (int row = 0; row < 7; row++) {
			for (int col = 0; col < 16; col++) {
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
		squares[selectedPlayer.getSquareX()][selectedPlayer.getSquareY()].setImage("");
		;

		selectedPlayer.setSquareX(selectedSquares.peek().getRelativeX());
		selectedPlayer.setSquareY(selectedSquares.peek().getRelativeY());

		new Move(selectedPlayer, gamePanel, selectedSquares.peek());

		removeSelection();
	}

	private void attack() {

		int damage = selectedPlayer.getAttack();

		for (Square square : selectedSquares) {

			if (square.getCurrentEnemy() != null) {
				Enemy currentEnemy = square.getCurrentEnemy();

				currentEnemy.setHealth(currentEnemy.getHealth() - damage);
				System.out.println(currentEnemy.getHealth());

				if (currentEnemy.getHealth() - damage <= 0) {
					liveEnemies.remove(currentEnemy);
					square.emptySquare();
				}
			}
		}

		removeSelection();
	}

	private int[] getAttackDirection(int playerSquareX, int playerSquareY, int mouseSquareX, int mouseSquareY) {

		int[] directions;
		int x = mouseSquareX - playerSquareX;
		int y = mouseSquareY - playerSquareY;

		if (x + y >= 0) {

			if (x >= y) {
				directions = new int[] { 1, 0 };
			} else {
				directions = new int[] { 0, 1 };
			}

		} else {

			if (x > y) {
				directions = new int[] { 0, -1 };
			} else {
				directions = new int[] { -1, 0 };
			}
		}

		return directions;
	}

	private Square getSqr(int x, int y) {

		return squares[Math.min(15, Math.max(0, (x - tileSize * 2) / tileSize))][Math.min(6,
				Math.max(0, (y - tileSize) / tileSize))];
	}

	private void setSqrImages() {

		if (lastHoveredSquare != null) {
			if (lastHoveredSquare.isInWalkRange()) {
				lastHoveredSquare.setImage("Range");
			} else {
				lastHoveredSquare.setImage("");
			}
		}

		if (hoveredSquare != null) {
			if (hoveredSquare.isInWalkRange()) {
				hoveredSquare.setImage("HoverRange");
			} else {
				hoveredSquare.setImage("Hover");
			}
		}

		if (selectedPlayer instanceof Warrior) {
			squares[selectedPlayer.getSquareX()][selectedPlayer.getSquareY()].setImage("Warrior");

		} else if (selectedPlayer instanceof Mage) {
			squares[selectedPlayer.getSquareX()][selectedPlayer.getSquareY()].setImage("Mage");

		} else if (selectedPlayer instanceof Archer) {
			squares[selectedPlayer.getSquareX()][selectedPlayer.getSquareY()].setImage("Archer");

		} else if (selectedPlayer instanceof Rogue) {
			squares[selectedPlayer.getSquareX()][selectedPlayer.getSquareY()].setImage("Rogue");
		}

		if (CombatStates.state == CombatStates.WAITING_INPUT) {
			for (Square square : selectedSquares) {
				square.setImage("SelectedRange");
			}
		} else if (CombatStates.state == CombatStates.SELECT_ATTACK_LOCATION) {

			for (Square square : selectedSquares) {
				square.setImage("SelectedRed");
			}
		} else {
			for (Square square : selectedSquares) {
				square.setImage("Selected");
			}
		}

		if (hoveredSquare != lastHoveredSquare) {
			lastHoveredSquare = hoveredSquare;
		}
	}

	private void removeSelection() {
		if (!selectedSquares.empty()) {
			if (CombatStates.state == CombatStates.WAITING_INPUT) {
				for (Square square : selectedSquares) {
					square.setImage("Range");
				}
			} else {
				for (Square square : selectedSquares) {
					square.setImage("");
				}
			}

			selectedSquares.removeAllElements();
		}
	}
}
