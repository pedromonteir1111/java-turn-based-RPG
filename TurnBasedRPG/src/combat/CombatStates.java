package combat;

import gamestates.Gamestate;

public enum CombatStates {

	WAITING_INPUT, ACTION_WALK;
	
	public static CombatStates state = WAITING_INPUT;
	
}
