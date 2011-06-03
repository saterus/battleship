package models;

import java.util.logging.Logger;

import models.Ship.HitStatus;

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
    private static final Logger LOGGER              = Logger.getLogger(BattleGridImpl.class
                                                            .getName());

    /**
     * BOARD_L is the length of the grid.
     */
    private static final int    BOARD_L             = 10;

    /** The number of ships possible to place on a single grid. */
    private static final int    TOTAL_INITIAL_SHIPS = 5;

    /** The number of ships left on the grid. */
    private int                 numShips            = TOTAL_INITIAL_SHIPS;

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
    private boolean             isActive            = false;

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
    public HitStatus shoot(int x, int y) {
        HitStatus shotStatus = HitStatus.MISS;

        if (gridSpaces[x][y].hasShip() && !gridSpaces[x][y].isVisible()) {
            Ship s = gridSpaces[x][y].getShip();
            shotStatus = s.hit();
            
            if (HitStatus.SUNK == shotStatus) {
                numShips--;
            }
        }

        gridSpaces[x][y].makeVisible();

		LOGGER.fine("Shot at (" + x + "," + y + "). Hit? "
				+ shotStatus.toString());
        return shotStatus;
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
    public ShipType shipTypeAt(int x, int y) {
        return gridSpaces[x][y].getShip().type();
    }
    
    @Override
    public boolean shipsRemaining() {
        return numShips > 0;
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

	/**
	 * Inner class which represents a square in the BattleGridImpl.
	 * 
	 * @author Group c421aa06
	 */
    private class GridSpace {

		/**
		 * The Ship, if any, residing at this grid space.
		 */
        private Ship    ship;

		/**
		 * Boolean to define the visibility of a grid space. True means the
		 * space is visible, false means it is not.
		 */
        private boolean visible;

		/**
		 * Default constructor. Initializes the GridSpace to contain no Ship and
		 * to be invisible.
		 */
        public GridSpace() {
            this.ship = null;
            this.visible = false;
        }

		/**
		 * Returns whether or not the GridSpace is visible.
		 * 
		 * @return true is the GridSpace is visible, and false otherwise.
		 */
        public boolean isVisible() {
            return this.visible;
        }

		/**
		 * Makes a GridSpace visible.
		 */
        public void makeVisible() {
            this.visible = true;
        }

		/**
		 * Returns whether or not the GridSpace contains a Ship.
		 * 
		 * @return true is there is a Ship at the GridSpace, and false
		 *         otherwise.
		 */
        public boolean hasShip() {
            return null != this.ship;
        }

		/**
		 * Returns the Ship residing at the GridSpace.
		 * 
		 * @return the Ship residing at the GridSpace, or null if there is no
		 *         Ship.
		 */
        public Ship getShip() {
            return this.ship;
        }

		/**
		 * Assigns a Ship to the GridSpace.
		 * 
		 * @param s
		 *            the Ship to be associated with the GridSpace.
		 */
        public void setShip(Ship s) {
            this.ship = s;
        }
    }
}
