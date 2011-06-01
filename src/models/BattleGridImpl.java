package models;

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
	 * BOARD_L is the length of the grid.
	 */

	private static final int BOARD_L = 10;

	/**
	 * gridSpace is a triple array. The first two levels of the array determine
	 * the x-position and y-position within the grid. The last level of the
	 * array contains two boolean values which determine two things:
	 * 
	 * At index 0, it tells whether or not a ship is occupying said space. At
	 * index 1, it tells whether or not a space has been hit, and is therefore
	 * visible.
	 */

	private final boolean[][][] gridSpace;
	
	/**
	 * isActive returns whether or not a player is active and it's their turn.
	 */
	private boolean isActive = false;

	/**
	 * Default constructor, which initializes the size of the grid.
	 */
	
	public BattleGridImpl() {
		gridSpace = new boolean[BOARD_L][BOARD_L][1];
	}

	@Override
	public void setShipPos(Ship ship, int x, int y, boolean orient) {
		boolean xParamFits = ((ship.type().length() + x) <= (BOARD_L - 1));
		boolean yParamFits = ((ship.type().length() + y) <= (BOARD_L - 1));

		if (xParamFits && yParamFits) {
			int lengthOfShip = ship.type().length();
			boolean allSpaceFree = true;

			// This while loop checks to make sure that all the spaces are free
			// from the start of the ship to its finish.

			while (lengthOfShip > 0 && allSpaceFree) {
				if (orient) {
					allSpaceFree = !(gridSpace[x + lengthOfShip - 1][y][0]);
				} else {
					allSpaceFree = !(gridSpace[x][y + lengthOfShip - 1][0]);
				}
				lengthOfShip--;
			}

			// Updates each space of the board, and tells it that a ship now
			// occupies it.
			if (allSpaceFree) {
				lengthOfShip = ship.type().length();
				while (lengthOfShip > 0) {
					if (orient) {
						gridSpace[x + lengthOfShip][y][0] = true;
					} else {
						gridSpace[x][y + lengthOfShip][0] = true;
					}
					lengthOfShip--;
				}
			}
		}
	}

	@Override
	public boolean shoot(int x, int y) {
		boolean didHit = false;
		if (gridSpace[x][y][0] && !gridSpace[x][y][1]) {
			gridSpace[x][y][1] = true;
			didHit = true;
		}
		return didHit;
	}

	@Override
	public boolean isViewable(int x, int y) {
		return gridSpace[x][y][1];
	}

	@Override
	public boolean isShip(int x, int y) {
		return gridSpace[x][y][0];
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
	
}
