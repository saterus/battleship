package controllers;

import java.util.logging.Logger;

import models.BattleGrid;
import models.Ship.HitStatus;
import models.ShipType;

/**
 * Tracks a target grid owned by the opposing player, upon which all shots are
 * fired.
 * 
 * @author Group c421aa06
 * 
 */
public final class FiringControllerImpl implements FiringController {

	/**
	 * A logger for use with the FiringControllerImpl class.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(FiringControllerImpl.class.getName());

	/**
	 * The grid being shot at.
	 */
	private final BattleGrid target;

	/**
	 * The WaitingController to be used with firing.
	 */
	private final WaitingController waiting;

	/**
	 * The message template to be displayed upon a Ship sinking.
	 */
	private final String sunkMessageTemplate = " has SUNK your ";
	
	   /**
     * The message template to be displayed upon a Ship being hit.
     */
    private final String hitMessageTemplate = " has hit your ";


	/**
	 * The message to be displayed upon a Ship sinking.
	 */
	private String hitMessage;

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
		this.hitMessage = null;
	}

	@Override
	public HitStatus fireShot(int x, int y) {

		HitStatus outcome = this.target.shoot(x, y);

		if (HitStatus.SUNK == outcome) {
			ShipType t = this.target.shipTypeAt(x, y);
			this.hitMessage = waiting.getActivePlayer().getPlayerName()
					+ this.sunkMessageTemplate + t.toString() + "! ";

			LOGGER.info(t.toString() + " has been sunk!");
		} else if (HitStatus.HIT == outcome) {
            ShipType t = this.target.shipTypeAt(x, y);
            this.hitMessage = waiting.getActivePlayer().getPlayerName()
                    + this.hitMessageTemplate + t.toString() + "! ";

            LOGGER.info(t.toString() + " has been sunk!");
        }

		if (!this.target.shipsRemaining()) {
			this.waiting.setHasWon(true);
			this.hitMessage += waiting.getActivePlayer().getPlayerName()
					+ " has sunk all your ships and won the game!";

			LOGGER.info(waiting.getActivePlayer().getPlayerName() + " has won!");
		}

		return outcome;
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
	    
//	    Find a better home for this. This doesn't work right here.
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e1) {
//            LOGGER.severe("FiringView Delay interrupted!");
//        }
	    
		String tempMsg = this.hitMessage;
		this.hitMessage = null;

		this.waiting.nextScreen(tempMsg);
	}

}
