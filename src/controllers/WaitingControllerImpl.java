package controllers;

import models.BattleGrid;
import models.Player;

/**
 * Controls the waiting round between placement and firing shots. Tracks which
 * player is the active player and then
 * 
 * @author Group c421aa06
 * 
 */
public final class WaitingControllerImpl implements WaitingController {

    // TODO: Maybe add a player field to the BattleGrid, or vice versa to cut
    // down on redundant parameters here.

    /**
     * The current player.
     */
    private Player     currentPlayer;
    /**
     * The inactive player.
     */
    private Player     inactivePlayer;

    /**
     * The grid owned by the current player.
     */
    private BattleGrid currentGrid;
    /**
     * The grid owned by the inactive player.
     */
    private BattleGrid inactiveGrid;

    /**
     * Setup controller with the current/inactive player/grids.
     * 
     * @param currentPlayer
     *            the currently active player.
     * @param currentGrid
     *            the currently active grid.
     * @param inactivePlayer
     *            the currently inactive player.
     * @param inactiveGrid
     *            the currently inactive grid.
     */
    public WaitingControllerImpl(Player currentPlayer, BattleGrid currentGrid,
            Player inactivePlayer, BattleGrid inactiveGrid) {
        this.currentPlayer = currentPlayer;
        this.currentGrid = currentGrid;

        this.inactivePlayer = inactivePlayer;
        this.inactiveGrid = inactiveGrid;
    }

    @Override
    public PlacementController switchPlacementPlayer() {
        swapPlayersAndGrids();

        return new PlacementControllerImpl(this, this.currentGrid);
    }

    @Override
    public FiringController switchFiringPlayer() {
        swapPlayersAndGrids();

        return new FiringControllerImpl(this, this.inactiveGrid);
    }

    /**
     * Swaps the current player and the inactive player member variables, as
     * well as the grids.
     */
    private void swapPlayersAndGrids() {
        Player tempPlayer = this.currentPlayer;
        BattleGrid tempGrid = this.currentGrid;

        this.currentPlayer = this.inactivePlayer;
        this.currentGrid = this.inactiveGrid;

        this.inactivePlayer = tempPlayer;
        this.inactiveGrid = tempGrid;
    }

}
