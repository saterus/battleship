package models;

/**
 * {@code Ship} implemented as a number of hits and a ShipType.
 * 
 * @correspondence {@code |this| = ShipType.length} and {@code numHits = }the
 *                 number of times the Ship has been hit.
 * @convention if {@code |this| = numHits, this} is sunk.
 * @author Group c421aa06
 */
public final class ShipImpl implements Ship {

	/**
	 * The number of hits that the Ship has taken.
	 */
	private int numHits = 0;

	/**
	 * The ShipType of the Ship.
	 */
	private ShipType type;

	/**
	 * Constructor which initializes a ship of the given type.
	 * 
	 * @param type
	 *            the type of the Ship object to create.
	 */
	public ShipImpl(ShipType type) {
		this.type = type;
	}

	@Override
	public ShipType type() {
		return type;
	}

	@Override
	public HitStatus hit() {
		numHits++;
		if (type.length() <= numHits) {
			return HitStatus.SUNK;
		}
		return HitStatus.HIT;
	}

}
