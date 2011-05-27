package cse421.battleship.models;

public interface Ship {

	/**
	 * Retrieves the ShipType associated with the Ship.
	 * 
	 * @return the ShipType of the Ship.
	 */
	ShipType type();

	/**
	 * Retrieves whether or not the ship has been sunk.
	 * 
	 * @return {@code true} if the length associated with the ShipType is equal
	 *         to the number of times the Ship has been hit, and {@code false}
	 *         otherwise.
	 */
	boolean isSunk();

	/**
	 * Registers a hit against the Ship.
	 */
	void hit();

}
