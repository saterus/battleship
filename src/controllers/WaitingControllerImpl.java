package controllers;

import java.util.ArrayList;
import java.util.List;

import models.BattleGrid;
import models.Player;
import views.BattleshipFrame;
import views.FiringView;
import views.PlacementView;
import views.WaitingView;
import views.WinView;

/**
 * Controls the waiting round between placement and firing shots. Tracks which
 * player is the active player and then
 * 
 * @author Group c421aa06
 * 
 */
public final class WaitingControllerImpl implements WaitingController {
    /**
     * The current player.
     */
    private Player            currentPlayer;
    /**
     * The inactive player.
     */
    private Player            inactivePlayer;

    /**
     * The grid owned by the current player.
     */
    private BattleGrid        currentGrid;

    /**
     * The BattleshipFrame using WaitingController as a view manager.
     */
    private BattleshipFrame   battleship;

    /**
     * The grid owned by the inactive player.
     */
    private BattleGrid        inactiveGrid;

    /**
     * The list maintaining the view sequence.
     */
    @SuppressWarnings("rawtypes")
    private final List<Class> viewSequence;

    /**
     * Counter to track the current position in the screen sequence.
     */
    private int               sequenceCounter = 0;

    /** Indicates when a player has won the game. */
    private boolean           hasWon;

    /**
     * Setup controller with the current/inactive player/grids.
     * 
     * @param battleship
     *            the BattleshipFrame for the game.
     * @param currentPlayer
     *            the currently active player.
     * @param currentGrid
     *            the currently active grid.
     * @param inactivePlayer
     *            the currently inactive player.
     * @param inactiveGrid
     *            the currently inactive grid.
     */
    @SuppressWarnings("rawtypes")
    public WaitingControllerImpl(BattleshipFrame battleship, Player currentPlayer,
            BattleGrid currentGrid, Player inactivePlayer, BattleGrid inactiveGrid) {
        this.battleship = battleship;

        this.viewSequence = new ArrayList<Class>();
        this.viewSequence.add(PlacementView.class);
        this.viewSequence.add(WaitingView.class);
        this.viewSequence.add(PlacementView.class);
        this.viewSequence.add(WaitingView.class);
        this.viewSequence.add(FiringView.class);

        this.currentPlayer = currentPlayer;
        this.currentGrid = currentGrid;
        this.currentGrid.setPlayerState(true);

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

    @Override
    public void nextScreen(String message) {
        if (hasWon) {
            this.battleship.switchView(WinView.class, message);
            
        } else {
            if (sequenceCounter == viewSequence.indexOf(FiringView.class)) {
                sequenceCounter--;
            } else {
                sequenceCounter++;
            }

            this.battleship.switchView(viewSequence.get(sequenceCounter), message);
        }
    }

    @Override
    public BattleGrid getActiveGrid() {
        return this.currentGrid;
    }

    @Override
    public Player getActivePlayer() {
        return this.currentPlayer;
    }

    @Override
    public BattleGrid getInactiveGrid() {
        return this.inactiveGrid;
    }

    @Override
    public Player getInactivePlayer() {
        return this.inactivePlayer;
    }

    @Override
    public void setHasWon(boolean b) {
        this.hasWon = b;
        this.inactiveGrid.setPlayerState(true); // so we see both grids as visible.
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
        this.currentGrid.setPlayerState(true);

        this.inactivePlayer = tempPlayer;
        this.inactiveGrid = tempGrid;
        this.inactiveGrid.setPlayerState(false);
    }

}
