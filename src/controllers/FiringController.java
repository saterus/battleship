package controllers;

import models.BattleGrid;
import models.Ship;

/**
 * A controller for firing for use in battleship. The user of this interface has
 * control over what rules existing regarding shooting.
 * 
 * @initially <br>
 *            constructor(WaitingController waiting, BattleGrid target):
 *            {@code ensures} the FiringController is associated with the
 *            waiting and target.
 * @author Group c421aa06
 */
public interface FiringController {

	/**
	 * Registers a shot at a position x,y on the grid.
	 * 
	 * @requires x and y are within 0-9 inclusive.
	 * @param x
	 *            the x-coordinate of the registered shot.
	 * @param y
	 *            the y-coordinate of the registered shot.
	 * @return {@code HIT} if the shot hit a Ship, {@code SUNK} if it hit and sunk a ship,
     * and {@code MISS} otherwise.
	 */
	Ship.HitStatus fireShot(int x, int y);

	/**
	 * Determines whether a spot on the grid is able to be shot. A space can be
	 * shot if it has never before been shot.
	 * 
	 * @param x
	 *            the x-coordinate of the position to check.
	 * @param y
	 *            the y-coordinate of the position to check.
	 * @return {@code true} if the position has not yet been shot, and
	 *         {@code false} otherwise.
	 */
	boolean isShootable(int x, int y);
	
	BattleGrid getTarget();
	
	void endTurn();

}
