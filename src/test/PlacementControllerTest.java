package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import models.BattleGrid;
import models.BattleGridImpl;
import models.Player;
import models.PlayerImpl;
import models.ShipType;

import org.junit.Before;
import org.junit.Test;

import views.BattleshipFrame;
import controllers.FiringController;
import controllers.FiringControllerImpl;
import controllers.PlacementController;
import controllers.PlacementControllerImpl;
import controllers.WaitingController;
import controllers.WaitingControllerImpl;

public class PlacementControllerTest {
	private WaitingController wait;
	private BattleshipFrame frame;
	private BattleGrid grid1, grid2;
	private Player player1, player2;
	private PlacementController place;

	@Before
	public void setUp() {
		player1 = new PlayerImpl("Player 1");
		player2 = new PlayerImpl("Player 2");
		grid1 = new BattleGridImpl(player1);
		grid2 = new BattleGridImpl(player2);
		frame = new BattleshipFrame();
		wait = new WaitingControllerImpl(frame, player1, grid1, player2, grid2);
		place = new PlacementControllerImpl(wait, grid1);
	}

	// Good ship placement with ship type
	@Test
	public void goodPlaceWithType() {
		place.setSelectedShipType(ShipType.BATTLESHIP);
		assertTrue("Good ship placement with ship type", place.setShipPos(5, 5));
	}

	// Outside ship placement
	@Test
	public void outsidePlace() {
		place.setSelectedShipType(ShipType.BATTLESHIP);
		assertTrue("Outside ship placement", !place.setShipPos(10, 10));
	}

	// Inside ship placement, reach outside
	@Test
	public void insidePlaceGoesOutside() {
		place.setSelectedShipType(ShipType.BATTLESHIP);
		assertTrue("Outside ship placement", !place.setShipPos(8, 8));
	}

	// Correct ShipType sizes
	@Test
	public void correctSizes() {
		place.setSelectedShipType(ShipType.AIRCRAFT_CARRIER);
		assertTrue("Aircraft carrier bad", !place.setShipPos(6, 5));
		assertTrue("Aircraft carrier good", place.setShipPos(5, 5));
		place.setSelectedShipType(ShipType.BATTLESHIP);
		assertTrue("Battleship bad", !place.setShipPos(7, 6));
		assertTrue("Battleship good", place.setShipPos(6, 6));
		place.setSelectedShipType(ShipType.DESTROYER);
		assertTrue("Destroyer bad", !place.setShipPos(8, 7));
		assertTrue("Destroyer good", place.setShipPos(7, 7));
		place.setSelectedShipType(ShipType.SUBMARINE);
		assertTrue("Submarine bad", !place.setShipPos(8, 8));
		assertTrue("Submarine good", place.setShipPos(7, 8));
		place.setSelectedShipType(ShipType.PATROL_BOAT);
		assertTrue("Patrol boat bad", !place.setShipPos(9, 9));
		assertTrue("Patrol boat good", place.setShipPos(8, 9));
	}

	// Rotation
	@Test
	public void rotation() {
		place.setSelectedShipType(ShipType.AIRCRAFT_CARRIER);
		assertTrue("Before rotation", !place.setShipPos(6, 3));
		place.rotateShip();
		assertTrue("After rotation", place.setShipPos(6, 3));
	}

	// Return rotation
	@Test
	public void returnRotation() {
		place.setSelectedShipType(ShipType.AIRCRAFT_CARRIER);
		place.rotateShip();
		assertTrue("Before rotation", !place.setShipPos(3, 6));
		place.rotateShip();
		assertTrue("After rotation", place.setShipPos(3, 6));
	}
}
