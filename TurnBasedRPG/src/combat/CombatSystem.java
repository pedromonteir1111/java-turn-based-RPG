package combat;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import entities.Archer;
import entities.Enemy;
import entities.Entity;
import entities.Goblin;
import entities.Mage;
import entities.Player;
import entities.Rogue;
import entities.Warrior;
import game.GamePanel;
import game.ScreenSettings;
import gamestates.Gamestate;
import inventory.PlayerInventory;
import items.Elixir;
import userInputs.Inputs;

public class CombatSystem {

	private ScreenSettings screenSettings;
	private HUD hud;
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
	private ArrayList<Player> liveAllies = new ArrayList<Player>();
	private ArrayList<Enemy> liveEnemies = new ArrayList<Enemy>();
	private PlayerInventory playerInventory;
	private Player selectedPlayer;
	private int playerIndex;
	int[] attackCoords;
	private int[] redCoords, lastRedCoords;
	private boolean combatWin;
	private boolean combatLoss;

	public CombatSystem(Player player, Player mage, Player rogue, ScreenSettings screenSettings, GamePanel gamePanel, PlayerInventory playerInventory, HUD hud) {


		this.allies = new Player[4];
		this.allies[0] = player;
		this.allies[1] = mage;
		this.allies[2] = rogue;
		this.tileSize = screenSettings.getTileSize();
		this.screenSettings = screenSettings;
		this.gamePanel = gamePanel;
		this.turns = 0;
		this.hud = hud;
		
		this.playerInventory = playerInventory;

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
				
				hud.setCurrentHud(2);

				break;

			case SPACE:

				if (!selectedSquares.empty()) {

					CombatStates.state = CombatStates.ACTION_WALK;
					runCombat(mouseX, mouseY, input);
				}
				
				hud.setCurrentHud(0);

				break;

			case Q:

				removeRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
				removeSelection();
				CombatStates.state = CombatStates.SELECT_ATTACK_LOCATION;
				
				hud.setCurrentHud(1);

				break;

			case NONE:

				break;

			default:
				break;

			}

			break;

		case ACTION_WALK:

			removeRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
			walk(selectedPlayer);
			attackCoords = null;
			redCoords = null;
			lastRedCoords = null;
			changeCharacter();

			break;

		case SELECT_ATTACK_LOCATION:

			int[] directions = getAttackDirection(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(),
					hoveredSquare.getRelativeX(), hoveredSquare.getRelativeY());

			attackCoords = selectedPlayer.attack(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), directions[0], directions[1]);
			
			//comparar referencia, nao valor
			if(lastRedCoords == redCoords) {
				lastRedCoords = null;
			} else {
				lastRedCoords = redCoords;
			}
			
			redCoords = attackCoords;
			

			switch (input) {

			case CLICK:

				Square clickedSquare = getSqr(mouseX, mouseY);

				if (selectedSquares.search(clickedSquare) == -1) {

					if (!selectedSquares.isEmpty()) {
						removeSelection();
					}

					for (int i = 0; i < attackCoords.length; i += 2) {
						if (attackCoords[i] < 16 && attackCoords[i] >= 0 && attackCoords[i + 1] < 7 && attackCoords[i + 1] >= 0) {
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
				
				hud.setCurrentHud(0);
				break;

			case SPACE:

				if (!selectedSquares.empty()) {

					CombatStates.state = CombatStates.ACTION_ATTACK;
					runCombat(mouseX, mouseY, input);
					
					hud.setCurrentHud(0);
				}
				
				break;

			default:
				break;
			}

			break;

		case ACTION_ATTACK:

			removeRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
			attack(selectedPlayer);
			attackCoords = null;
			redCoords = null;
			lastRedCoords = null;
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
	
	public void startCombat(int[] enemies) {
		
		combatLoss = false;
		combatWin = false;
		turns = 0;
		
		Gamestate.state = Gamestate.COMBAT;
		createEntities(enemies);
		runCombat(-1, -1, Inputs.NONE);
	}

	private void showRange(int originX, int originY, int range) {

		if (lastRedCoords != null) {
			for (int i = 0; i < lastRedCoords.length; i += 2) {
				if (lastRedCoords[i] < 16 && lastRedCoords[i] >= 0 && lastRedCoords[i + 1] < 7
						&& lastRedCoords[i + 1] >= 0) {
					squares[lastRedCoords[i]][lastRedCoords[i + 1]].setImage("");
				}
			} 
		}
		
		Stack<Square> inRangeSquares = inRange(originX, originY, range);
		
		while(!inRangeSquares.empty()) {
			
			Square square = inRangeSquares.pop();
			
			square.setImage("Range");
			square.setInWalkRange(true);
		}
		
		
	}

	private void removeRange(int originX, int originY, int range) {

		Stack<Square> inRangeSquares = inRange(originX, originY, range);
		
		while(!inRangeSquares.empty()) {
			
			Square square = inRangeSquares.pop();
			
			square.setImage("");
			square.setInWalkRange(false);
		}
	}
	
	private Stack<Square> inRange(int originX, int originY, int range) {

		Stack<Square> inRangeSquares = new Stack<Square>();
		
		for (int i = -range; i <= range; i++) {
			for (int j = -range; j <= range; j++) {

				if (Math.abs(i) + Math.abs(j) <= range && i + originX >= 0 && i + originX < 16 && j + originY >= 0 && j + originY < 7) {
					
					inRangeSquares.push(squares[i + originX][j + originY]);
				}
			}
		}
		
		return inRangeSquares;
	}

	private void changeCharacter() {

		playerIndex++;

		if (playerIndex > liveAllies.size() - 1) {
			playerIndex = 0;
			
			CombatStates.state = CombatStates.ENEMY_TURN;
			ControlEnemies();
			if (combatWin || combatLoss) return;
			
		} else {
			CombatStates.state = CombatStates.WAITING_INPUT;
		}	
		
		selectedPlayer = liveAllies.get(playerIndex);
		
		if(selectedPlayer instanceof Warrior) {
			
			hud.setCurrentPlayer(0);
			
		} else if(selectedPlayer instanceof Mage) {
			
			hud.setCurrentPlayer(1);
			
		} else if(selectedPlayer instanceof Rogue) {
			
			hud.setCurrentPlayer(2);
		}
		
		showRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
	}

//	private void removeElixirIfUsed() { // removendo o efeito do elixir (se coletado) após o combate
//
//		Elixir elixir = (Elixir) playerInventory.findItem("Elixir Milagroso");
//		
//		if (elixir != null && elixir.isUsed()) {
//			
//			elixir.removingElixirEffect(selectedPlayer);
//			
//			playerInventory.removeItem(elixir);
//	
//			System.out.println("[ELIXIR] O efeito do seu elixir acabou!");
//		}
//	}

	private void initCombat() {

		combatLoss = false;
		combatWin = false;
		
//		Elixir elixir = (Elixir) playerInventory.findItem("Elixir Milagroso");
//		
//		if (elixir != null && elixir.isCollected()) {
//			
//			elixir.use(selectedPlayer);
//			System.out.println("[ELIXIR] O elixir foi usado. Aproveite o boost!");
//		}
		
		for (Player ally : allies) {
			if (ally != null) {
				liveAllies.add(ally);
			}
		}

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
			
			ally.setCurrentPlayerPosition(ally.getRightDirection()[1]);
			
			ally.setHealth(ally.getMaxHealth());
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
			
			enemy.setCurrentPlayerPosition(enemy.getLeftDirection()[1]);
		}

		CombatStates.state = CombatStates.WAITING_INPUT;
		selectedPlayer = liveAllies.get(0);
		turns++;
		playerIndex = 0;

		showRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
	}

	public void leaveCombat() {
		
		int randomGoldDrop = (int)(Math.random() * 30 + 20); // drop aleatório de ouro que varia entre 20 e 50
		
		for (int row = 0; row < 7; row++) {
			for (int col = 0; col < 16; col++) {

				squares[col][row].emptySquare();
				squares[col][row].setImage("");
			}
		}

		if(liveEnemies.isEmpty()) {
			combatWin = true;
			combatLoss = false;
			
			System.out.println("[OURO] Você recebeu " + randomGoldDrop + " de ouro dos seus inimigos!");
			playerInventory.setGold(playerInventory.getGold() + randomGoldDrop); // adicionando o drop de ouro no inventário do player (guerreiro) ao finalizar o combate
			playerInventory.setGold(playerInventory.getGold() + 50); // adicionando 50 de ouro ao vencer o combate
			
		} else if (liveAllies.isEmpty()) {
			combatWin = false;
			combatLoss = true;
			
			System.out.println("[OURO] Você perdeu toda sua riqueza nesse combate!");

			playerInventory.setGold(0); // se perder o combate, fica zerado de ouro
		}
		
		Gamestate.state = Gamestate.PLAYING;
		this.turns = 0;
		hud.setCurrentHud(0);
		hud.setCurrentPlayer(0);
		removeSelection();
		removeRange(selectedPlayer.getSquareX(), selectedPlayer.getSquareY(), selectedPlayer.getWalkRange());
		liveAllies.clear();
		liveEnemies.clear();
		
//		removeElixirIfUsed();
	}

	public void createEntities(int[] enemyTypes) {
		// 0 eh goblin, 1 slime e 2 skeleton

		System.out.println(liveEnemies);
		
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

	public void drawEntities(Graphics2D g2D) {
		for (Enemy enemy : liveEnemies) {
			enemy.draw(g2D);
		}
		
		for (Player player : liveAllies) {
			player.draw(g2D);
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

	private void walk(Entity entity) {

		squares[entity.getSquareX()][entity.getSquareY()].emptySquare();
		squares[entity.getSquareX()][entity.getSquareY()].setImage("");

		entity.setSquareX(selectedSquares.peek().getRelativeX());
		entity.setSquareY(selectedSquares.peek().getRelativeY());
		
		if (entity instanceof Player) {
			squares[entity.getSquareX()][entity.getSquareY()].setCurrentPlayer((Player) entity);
		} else {
			squares[entity.getSquareX()][entity.getSquareY()].setCurrentEnemy((Enemy) entity);
		}
		
		new Move(entity, gamePanel, selectedSquares.peek());

		removeSelection();
	}

	private void attack(Entity entity) {

		int damage = entity.getAttack();

		if(entity instanceof Player) {
			
			//stack percorrido por for each ao inves de while (!selectedSquares.empty()) para poder remover a selecao posteriormente
			
			for (Square square : selectedSquares) {
				
				if (square.getCurrentEnemy() != null) {
					Enemy currentEnemy = square.getCurrentEnemy();
					
					currentEnemy.setHealth(currentEnemy.getHealth() - damage);
					
					if (currentEnemy.getHealth() <= 0) {
						liveEnemies.remove(currentEnemy);
						square.emptySquare();
					}
				}
			}
			
		} else {
			
			for (Square square : selectedSquares) {
				
				if (square.getCurrentPlayer() != null) {
					
					Player currentPlayer = square.getCurrentPlayer();
					
					currentPlayer.setHealth(currentPlayer.getHealth() - damage);
					
					if (currentPlayer.getHealth() <= 0) {
						liveAllies.remove(currentPlayer);
						if (!liveAllies.isEmpty()) {
							playerIndex = 0;
							selectedPlayer = liveAllies.get(0);
						}
						square.emptySquare();
					}
				}
			}
		}
		
		if(liveAllies.isEmpty() || liveEnemies.isEmpty()) {
			leaveCombat();
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

		if (CombatStates.state == CombatStates.SELECT_ATTACK_LOCATION && lastRedCoords != null) {
			for (int i = 0; i < lastRedCoords.length; i += 2) {
				if (lastRedCoords[i] < 16 && lastRedCoords[i] >= 0 && lastRedCoords[i + 1] < 7
						&& lastRedCoords[i + 1] >= 0) {
					squares[lastRedCoords[i]][lastRedCoords[i + 1]].setImage("");
				}
			} 
		}
		
		if (lastHoveredSquare != null) {
			if (lastHoveredSquare.isInWalkRange()) {
				lastHoveredSquare.setImage("Range");
			} else {
				lastHoveredSquare.setImage("");
			}
		}

		if (hoveredSquare != null && CombatStates.state != CombatStates.SELECT_ATTACK_LOCATION) {
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
		
		if (CombatStates.state == CombatStates.SELECT_ATTACK_LOCATION && attackCoords != null && redCoords != null) {
			
			for (int i = 0; i < redCoords.length; i += 2) {
				if (redCoords[i] < 16 && redCoords[i] >= 0 && redCoords[i + 1] < 7 && redCoords[i + 1] >= 0) {
					squares[attackCoords[i]][attackCoords[i + 1]].setImage("Red");
				}
			}
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
	
	private void ControlEnemies() {
		
		int[] directions;
		
		for (Enemy enemy : liveEnemies) {
			
			Square closestSquare = null;
			Player closestPlayer = null;
			boolean gonnaAttack = false;
			
			for (Player player : liveAllies) {
				
				if(closestPlayer == null) {
					
					closestPlayer = player;
					
				} else if (getManhattanDistance(enemy.getSquareX(), enemy.getSquareY(), player.getSquareX(), player.getSquareY())
						< getManhattanDistance(enemy.getSquareX(), enemy.getSquareY(), closestPlayer.getSquareX(), closestPlayer.getSquareY())) {
					
					closestPlayer = player;
				}
				
				if(getManhattanDistance(enemy.getSquareX(), enemy.getSquareY(), player.getSquareX(), player.getSquareY()) <= enemy.getAttackRange()) {
					
					gonnaAttack = true;
					
					directions = getAttackDirection(enemy.getSquareX(), enemy.getSquareY(), player.getSquareX(), player.getSquareY());
					
					attackCoords = enemy.attack(enemy.getSquareX(), enemy.getSquareY(), directions[0], directions[1]);
					
					for (int i = 0; i < attackCoords.length; i += 2) {
						if (attackCoords[i] < 16 && attackCoords[i] >= 0 && attackCoords[i + 1] < 7 && attackCoords[i + 1] >= 0) {
							selectedSquares.push(squares[attackCoords[i]][attackCoords[i + 1]]);
						}
						
						setSqrImages();
						gamePanel.repaint();
						gamePanel.revalidate();				

					}
				}	
			}
			
			if(!gonnaAttack) {
				
				Stack<Square> inRangeSquares = inRange(enemy.getSquareX(), enemy.getSquareY(), enemy.getWalkRange());
				
				while(!inRangeSquares.empty()) {
					
					Square square = inRangeSquares.pop();
					
					if(closestSquare == null && square.getCurrentEnemy() == null && square.getCurrentPlayer() == null) {
						
						closestSquare = square;
						
					} 
					
					if (closestSquare != null) {
						
						if (getManhattanDistance(square.getRelativeX(), square.getRelativeY(),
								closestPlayer.getSquareX(), closestPlayer.getSquareY()) < getManhattanDistance(
										closestSquare.getRelativeX(), closestSquare.getRelativeY(),
										closestPlayer.getSquareX(), closestPlayer.getSquareY())
								&& square.getCurrentEnemy() == null && square.getCurrentPlayer() == null) {

							closestSquare = square;
						} 
					}
				}
				
				if (closestSquare != null) {
					selectedSquares.push(closestSquare);
					walk(enemy);
					removeSelection();
				}
				
			} else {
				
				attack(enemy);
				removeSelection();
				
			}		
			
			if(combatLoss || combatWin) return;
			
		}
		
		CombatStates.state = CombatStates.WAITING_INPUT;
		turns++;
	}
	
	public boolean getCombatLoss() {
		return this.combatLoss;
	}
	
	public boolean getCombatWin() {
		return this.combatWin;
	}
}
