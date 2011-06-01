package controllers;

import models.BattleGrid;

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
	private BattleGrid target;

	/**
	 * The WaitingController to be used with firing.
	 */
	private WaitingController waiting;

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
	public boolean fireShot(int x, int y) {
		return this.target.shoot(x, y);
	}

	@Override
	public boolean isShootable(int x, int y) {
		return !this.target.isViewable(x, y);
	}

}
