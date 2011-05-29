package models;

/**
 * An enumeration class defining possible ship types to be used with ShipImpl.
 * 
 * @author Group c421aa06
 */
public enum ShipType {

	/**
	 * Associate AIRCRAFT_CARRIER with a length of 5.
	 */
	AIRCRAFT_CARRIER(5),

	/**
	 * Associate BATTLESHIP with a length of 4.
	 */
	BATTLESHIP(4),

	/**
	 * Associate DESTROYER with a length of 3.
	 */
	DESTROYER(3),

	/**
	 * Associate SUBMARINE with a length of 3.
	 */
	SUBMARINE(3),

	/**
	 * Associate PATROL_BOAT with a length of 2.
	 */
	PATROL_BOAT(2);

	/**
	 * The length associated with the ShipType.
	 */
	private int length;

	/**
	 * Create a ShipType of length {@code length}.
	 * 
	 * @param length
	 *            the length associated with the ShipType.
	 */
	private ShipType(int length) {
		this.length = length;
	}

	/**
	 * Retrieves the length associated with the ShipType.
	 * 
	 * @return the length associated with the ShipType.
	 */
	public int length() {
		return length;
	}

	@Override
	public String toString() {
		String[] s = super.toString().split("_");
		String name = s[0].substring(0, 1) + s[0].substring(1).toLowerCase();
		if (s.length == 2) {
			name = name + " " + s[1].substring(0, 1)
					+ s[1].substring(1).toLowerCase();
		}
		return name;
	}

}
