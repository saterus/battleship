package models;

/**
 * A ship for use in battleship. The user of this interface has control over
 * what possible ship types exist and what rules determine when a Ship is sunk.
 * 
 * @mathmodel an array of positions of the length associated with the ShipType.
 * @constraint Ship must be sunk if all array positions have been hit.
 * @initially <br>
 *            constructor(ShipType type): {@code ensures} the type of the Ship
 *            is initialized to type.
 * @author Group c421aa06
 */
public interface Ship {

    /** Status returned when a shot has been fired near a Ship. */
    public enum HitStatus { MISS, HIT, SUNK };
    
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
	HitStatus hit();

}
