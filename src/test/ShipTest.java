package test;

import static org.junit.Assert.assertEquals;
import models.Ship;
import models.Ship.HitStatus;
import models.ShipImpl;
import models.ShipType;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {
	private Ship ship;
	private HitStatus status;

	@Before
	public void setUp() {

	}

	// Construct patrol boat
	@Test
	public void constructPatrolBoat() {
		ship = new ShipImpl(ShipType.PATROL_BOAT);
		assertEquals("Construct patrol boat", ShipType.PATROL_BOAT, ship.type());
	}

	// Construct submarine
	@Test
	public void constructSubmarine() {
		ship = new ShipImpl(ShipType.SUBMARINE);
		assertEquals("Construct submarine", ShipType.SUBMARINE, ship.type());
	}

	// Construct destroyer
	@Test
	public void constructDestroyer() {
		ship = new ShipImpl(ShipType.DESTROYER);
		assertEquals("Construct destroyer", ShipType.DESTROYER, ship.type());
	}

	// Construct battleship
	@Test
	public void constructBattleship() {
		ship = new ShipImpl(ShipType.BATTLESHIP);
		assertEquals("Construct battleship", ShipType.BATTLESHIP, ship.type());
	}

	// Construct aircraft carrier
	@Test
	public void constructAircraftCarrier() {
		ship = new ShipImpl(ShipType.AIRCRAFT_CARRIER);
		assertEquals("Construct aircraft carrier", ShipType.AIRCRAFT_CARRIER,
				ship.type());
	}

	// One hit
	@Test
	public void oneHit() {
		ship = new ShipImpl(ShipType.SUBMARINE);
		status = ship.hit();
		assertEquals("One hit", HitStatus.HIT, status);
	}

	// Sunk
	@Test
	public void sunk() {
		ship = new ShipImpl(ShipType.SUBMARINE);
		ship.hit();
		ship.hit();
		status = ship.hit();
		assertEquals("Sunk", HitStatus.SUNK, status);
	}

	// More than sunk
	@Test
	public void moreThanSunk() {
		ship = new ShipImpl(ShipType.SUBMARINE);
		ship.hit();
		ship.hit();
		ship.hit();
		status = ship.hit();
		assertEquals("More than sunk", HitStatus.SUNK, status);
	}
}
