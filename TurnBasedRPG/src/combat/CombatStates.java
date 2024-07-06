package combat;

public enum CombatStates {

	WAITING_INPUT, ACTION_WALK, SELECT_ATTACK_LOCATION, ACTION_ATTACK;
	
	public static CombatStates state = WAITING_INPUT;
	
}
