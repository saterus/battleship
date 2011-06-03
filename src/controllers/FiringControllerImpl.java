package controllers;

import models.BattleGrid;
import models.Ship;

/**
 * Tracks a target grid owned by the opposing player, upon which all shots are
 * fired.
 * 
 * @author Group c421aa06
 * 
 */
public final class FiringControllerImpl implements FiringController {

	/**
	 * The grid being shot at.
	 */
	private final BattleGrid target;

	/**
	 * The WaitingController to be used with firing.
	 */
	private final WaitingController waiting;

	/**
	 * Sets up the controller with the grid to fire upon.
	 * 
	 * @param waiting
	 *            the WaitingController to be used with firing.
	 * @param target
	 *            the grid owned by the opposing player.
	 */
	public FiringControllerImpl(WaitingController waiting, BattleGrid target) {
		this.waiting = waiting;
		this.target = target;
	}

	@Override
	public Ship.HitStatus fireShot(int x, int y) {
		return this.target.shoot(x, y);
	}

	@Override
	public boolean isShootable(int x, int y) {
		return !this.target.isViewable(x, y);
	}
	
	@Override
	public BattleGrid getTarget() {
	    return this.target;
	}
	
	@Override
	public void endTurn() {
	    this.waiting.nextScreen();
	}

}
