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

	/**
	 * Switches the game view to the next sequential screen display.
	 * 
	 * @param message
	 *            the message to be displayed to the player.
	 */
	void nextScreen(String message);

	/**
	 * Retrieves the active BattleGrid.
	 * 
	 * @return the BattleGrid that is currently active.
	 */
	BattleGrid getActiveGrid();

	/**
	 * Retrieves the active Player.
	 * 
	 * @return the Player that is currently active.
	 */
	Player getActivePlayer();

	/**
	 * Retrieves the inactive BattleGrid.
	 * 
	 * @return the BattleGrid that is currently inactive.
	 */
	BattleGrid getInactiveGrid();

	/**
	 * Retrieves the inactive Player.
	 * 
	 * @return the Player that is currently inactive.
	 */
	Player getInactivePlayer();

	/**
	 * Notify the game that it is over and the current active player has won.
	 * 
	 * @param b
	 *            a boolean denoting whether or not the game has been won. True
	 *            signifies that the game has been won.
	 */
	void setHasWon(boolean b);

}
