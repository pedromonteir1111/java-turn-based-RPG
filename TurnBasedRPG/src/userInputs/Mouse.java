package userInputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import combat.CombatSystem;
import gamestates.Gamestate;

public class Mouse extends MouseAdapter {

	private CombatSystem combat;
	
	private int x, y;
	
	public Mouse(CombatSystem combat) {
		this.combat = combat;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {

		if(Gamestate.state == Gamestate.COMBAT) {
			
			Inputs.lastInput = Inputs.CLICK;
			combat.runCombat(x, y, Inputs.CLICK);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(Gamestate.state == Gamestate.COMBAT) {
			combat.runCombat(x, y, Inputs.NONE);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		if(Gamestate.state == Gamestate.COMBAT) {
			
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		if(Gamestate.state == Gamestate.COMBAT) {
			combat.runCombat(x, y, Inputs.NONE);
		}
	}
}
