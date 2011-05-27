package cse421.battleship.models;

public final class ShipImpl implements Ship {
	
	/**
	 * The number of hits that the Ship has taken.
	 */
	private int numHits = 0;
	
	/**
	 * The ShipType of the Ship.
	 */
	private ShipType type;

	@Override
	public ShipType type() {
		return type;
	}

	@Override
	public boolean isSunk() {
		return (type.length() == numHits);
	}

	@Override
	public void hit() {
		numHits++;
	}

}
