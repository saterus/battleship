package models;

import java.util.logging.Logger;

/**
 * BattleGrid implemented as a triple array.
 * 
 * @correspondence triple array of grid positions = gridSpace.
 * @convention all actions taken on the grid must be within the designated size
 *             of the grid.
 * @author Group c421aa06
 */
public final class BattleGridImpl implements BattleGrid {

    /**
     * A LOGGER to be used with the BattleGridImpl class.
     */
    private static final Logger LOGGER   = Logger.getLogger(BattleGridImpl.class
                                                 .getName());

    /**
     * BOARD_L is the length of the grid.
     */

    private static final int    BOARD_L  = 10;

    /**
     * gridSpace is a triple array. The first two levels of the array determine
     * the x-position and y-position within the grid. The last level of the
     * array contains two boolean values which determine two things:
     * 
     * At index 0, it tells whether or not a ship is occupying said space. At
     * index 1, it tells whether or not a space has been hit, and is therefore
     * visible.
     */

    private final GridSpace[][] gridSpaces;

    /**
     * isActive returns whether or not a player is active and it's their turn.
     */
    private boolean             isActive = false;

    /**
     * Default constructor, which initializes the size of the grid.
     */

    public BattleGridImpl() {
        gridSpaces = new GridSpace[BOARD_L][BOARD_L];
        for (int i = 0; i < BOARD_L; i++) {
            for (int j = 0; j < BOARD_L; j++) {
                gridSpaces[i][j] = new GridSpace();
            }
        }
        LOGGER.finest("Created BattleGrid.");
    }

    @Override
    public void setShipPos(Ship ship, int x, int y, boolean orient) {

        int lengthOfShip = ship.type().length();

        // Updates each space of the board, and tells it that a ship now
        // occupies it.
        if (orient) {
            for (int i = 0; i < lengthOfShip; i++) {
                gridSpaces[x + i][y].setShip(ship);
            }
        } else {
            for (int i = 0; i < lengthOfShip; i++) {
                gridSpaces[x][y + i].setShip(ship);
            }
        }

        LOGGER.fine("Set ship at (" + x + "," + y + ")");
    }

    @Override
    public boolean shoot(int x, int y) {
        boolean didHit = false;
        if (gridSpaces[x][y].hasShip() && !gridSpaces[x][y].isVisible()) {
            didHit = true;
        }

        gridSpaces[x][y].makeVisible();

        LOGGER.fine("Shot at (" + x + "," + y + "). Hit? " + didHit);
        return didHit;
    }

    @Override
    public boolean isViewable(int x, int y) {
        return gridSpaces[x][y].isVisible();
    }

    @Override
    public boolean isShip(int x, int y) {
        return gridSpaces[x][y].hasShip();
    }

    @Override
    public boolean boundsCheck(int x, int y) {
        return x < BOARD_L && y < BOARD_L && x >= 0 && y >= 0;
    }

    @Override
    public int gridSize() {
        return BOARD_L;
    }

    @Override
    public void setPlayerState(boolean state) {
        isActive = state;
    }

    @Override
    public boolean getPlayerState() {
        return isActive;
    }

    private class GridSpace {

        private Ship    ship;

        private boolean visible;

        public GridSpace() {
            this.ship = null;
            this.visible = false;
        }

        public boolean isVisible() {
            return this.visible;
        }
        
        public void makeVisible() {
            this.visible = true;
        }

        public boolean hasShip() {
            return null != this.ship;
        }

        public Ship getShip() {
            return this.ship;
        }

        public void setShip(Ship s) {
            this.ship = s;
        }
    }

}
