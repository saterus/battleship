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
    
    private static final Logger LOGGER = Logger.getLogger(FiringControllerImpl.class
            .getName());

    /**
     * The grid being shot at.
     */
    private final BattleGrid        target;

    /**
     * The WaitingController to be used with firing.
     */
    private final WaitingController waiting;

    private static final String     sunkMessageTemplate = " has sunk your ";

    private String                  sunkMessage;

    private boolean                 hasWon;

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
        this.sunkMessage = null;
        this.hasWon = false;
    }

    @Override
    public HitStatus fireShot(int x, int y) {

        HitStatus outcome = this.target.shoot(x, y);

        if (HitStatus.SUNK == outcome) {
            ShipType t = this.target.shipTypeAt(x, y);
            this.sunkMessage = waiting.getActivePlayer().getPlayerName()
                    + this.sunkMessageTemplate + t.toString();
            
            LOGGER.info(t.toString() + " has been sunk!");
        }

        if (!this.target.shipsRemaining()) {
            this.hasWon = true;
            this.sunkMessage += waiting.getActivePlayer().getPlayerName()
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
        // TODO: More thinking about the repercussions of how to display a
        // message on the WaitingView.
        
        // if (null == sunkMessage) {
        this.waiting.nextScreen();
        // } else {
        // String tempMsg = this.sunkMessage;
        // this.sunkMessage = null;
        // this.waiting.nextScreen(tempMsg);
        // }
    }

}
