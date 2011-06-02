package controllers;

import models.BattleGrid;
import models.Player;

/**
 * A controller for Player view changing for use in battleship. The user of this
 * interface has control over what causes the view to change.
 * 
 * @initially <br>
 *            constructor(BattleshipFrame battleship, Player currentPlayer,
 *            BattleGrid currentGrid, Player inactivePlayer, BattleGrid
 *            inactiveGrid): {@code ensures} the WaitingController is associated
 *            with battleship, both the current and inactive Player, and the
 *            current and inactive grid.
 * @author Group c421aa06
 */
public interface WaitingController {

	/**
	 * Switches the game view between the two Players during placement.
	 * 
	 * @return the new controller for placing the next player's pieces.
	 */
	PlacementController switchPlacementPlayer();

	/**
	 * Switches the game view between the two Players during firing.
	 * 
	 * @return the new controller with the correct player attacking and
	 *         defending.
	 */
	FiringController switchFiringPlayer();
	
	void nextScreen();
	
	BattleGrid getActiveGrid();
	
	Player getActivePlayer();

}
