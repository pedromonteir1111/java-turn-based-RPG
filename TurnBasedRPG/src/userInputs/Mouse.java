package userInputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import combat.CombatStates;
import combat.CombatSystem;
import gamestates.Gamestate;
import ui.InventoryUI;

public class Mouse extends MouseAdapter {

	private CombatSystem combat;
	private InventoryUI inventoryUI;
	
	private int x, y;
	
	public Mouse(CombatSystem combat, InventoryUI inventoryUI) {
		
		this.combat = combat;
		this.inventoryUI = inventoryUI;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {

		if(Gamestate.state == Gamestate.COMBAT && CombatStates.state != CombatStates.ENEMY_TURN) {
			
			Inputs.lastInput = Inputs.CLICK;
			combat.runCombat(x, y, Inputs.CLICK);
		}
		
		else if(inventoryUI.isVisible()) {
			inventoryUI.handleMouseClick(e.getPoint());
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(Gamestate.state == Gamestate.COMBAT && CombatStates.state != CombatStates.ENEMY_TURN) {
			combat.runCombat(x, y, Inputs.NONE);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		if(Gamestate.state == Gamestate.COMBAT && CombatStates.state != CombatStates.ENEMY_TURN) {
			
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		if(Gamestate.state == Gamestate.COMBAT && CombatStates.state != CombatStates.ENEMY_TURN) {
			combat.runCombat(x, y, Inputs.NONE);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(inventoryUI.isVisible()) {
			inventoryUI.handleMouseClick(e.getPoint());
		}
	}
}
