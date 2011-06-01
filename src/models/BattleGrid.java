package models;

// TODO add any constructors with @initially

/**
 * A gridmap for use in battleship. The user of this interface has control over
 * grid size and how grid space is managed.
 * 
 * @mathmodel an double array of positions of the dimensions of the grid.
 * @constraint all Ship objects placed on the grid must fit entirely within the
 *             defined dimensions.
 * @author Group c421aa06
 */
public interface BattleGrid {

	/**
	 * Assigns positions in the grid to the specified Ship.
	 * 
	 * @requires 0 <= x <= 9 and 0 <= y <= 9
	 * @ensures A ship is set within the given position assuming
	 * 			no other ship occupies those spaces.
	 * 
	 * @param ship
	 *            the Ship to place on the grid
	 * @param x
	 *            the x-coordinate of the position of the head of the Ship.
	 * @param y
	 *            the y-coordinate of the position of the head of the Ship.
	 * @param orient
	 *            the orientation of the Ship ({@code true} if oriented along
	 *            the x-axis, and {@code false} if along the y).
	 *            
	 */
	void setShipPos(Ship ship, int x, int y, boolean orient);

	/**
	 * Registers a shot at position x,y on the grid.
	 * 
	 * @requires 0 <= x <= 9 and 0 <= y <= 9
	 * 
	 * @param x
	 *            the x-coordinate of the position to shoot.
	 * @param y
	 *            the y-coordinate of the position to shoot.
	 * @return {@code true} if the shot hit a Ship, and {@code false} otherwise.
	 */
	boolean shoot(int x, int y);

	/**
	 * Determines whether a grid position is viewable. A grid position is
	 * viewable if it has been shot.
	 * 
	 * @requires 0 <= x <= 9 and 0 <= y <= 9
	 * 
	 * @param x
	 *            the x-coordinate of the position to check for visibility.
	 * @param y
	 *            the y-coordinate of the position to check for visibility.
	 * @return {@code true} if the position is visible, and {@code false}
	 *         otherwise.
	 */
	boolean isViewable(int x, int y);

	/**
	 * Determines whether a grid position is associated with a Ship.
	 * 
	 * @requires 0 <= x <= 9 and 0 <= y <= 9
	 * 
	 * @param x
	 *            the x-coordinate of the position to check for a Ship.
	 * @param y
	 *            the y-coordinate of the position to check for a Ship.
	 * @return {@code true} if the position is associated with a Ship, and
	 *         {@code false} otherwise.
	 */
	boolean isShip(int x, int y);
	
	/**
	 * 
	 * @param x
	 *            the x-coordinate of the position to check for a Ship.
	 * @param y
	 *            the y-coordinate of the position to check for a Ship.
	 * @return {@code true} if the position is within the grid space.
	 * 		   {@code false} if the position is outside the grid space.
	 */
	boolean boundsCheck(int x, int y);

	// TODO remove this before submitting the project!
	// public boolean isRevealed(int x, int y); Removed because it leaks
	// information.
}
