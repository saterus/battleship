package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import models.BattleGrid;
import models.BattleGridImpl;
import models.Player;
import models.PlayerImpl;
import models.Ship;
import models.ShipImpl;
import models.ShipType;

import org.junit.Before;
import org.junit.Test;

public class BattleGridTest {
	private BattleGrid grid;
	private Player player;

	@Before
	public void setUp() {
		player = new PlayerImpl("Test Player");
	}

	// Default constructor
	@Test
	public void defaultConstructor() {
		grid = new BattleGridImpl(player);
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				assertTrue("Viewable?", !grid.isViewable(x, y));
				assertTrue("Ship?", !grid.isShip(x, y));
			}
		}
	}

	// Set ship
	@Test
	public void setShip() {
		grid = new BattleGridImpl(player);
		Ship ship = new ShipImpl(ShipType.SUBMARINE);
		grid.setShipPos(ship, 5, 5, true);
		assertTrue("Ship1?", grid.isShip(5, 5));
		assertTrue("Ship2?", grid.isShip(6, 5));
		assertTrue("Ship3?", grid.isShip(7, 5));
	}

	// Shoot ship
	@Test
	public void shootShip() {
		grid = new BattleGridImpl(player);
		Ship ship = new ShipImpl(ShipType.SUBMARINE);
		grid.setShipPos(ship, 5, 5, true);
		assertEquals("Shot 1", Ship.HitStatus.HIT, grid.shoot(5, 5));
		assertEquals("Shot 2", Ship.HitStatus.HIT, grid.shoot(6, 5));
		assertEquals("Shot 3", Ship.HitStatus.SUNK, grid.shoot(7, 5));
		assertEquals("Shot 4", Ship.HitStatus.MISS, grid.shoot(8, 5));
	}

	// Find ship type
	@Test
	public void findShipType() {
		grid = new BattleGridImpl(player);
		Ship ship1 = new ShipImpl(ShipType.SUBMARINE);
		Ship ship2 = new ShipImpl(ShipType.BATTLESHIP);
		grid.setShipPos(ship1, 5, 5, true);
		grid.setShipPos(ship2, 4, 5, false);
		assertEquals("Check 1 - sub", ShipType.SUBMARINE, grid.shipTypeAt(6, 5));
		assertEquals("Check 2 - bat", ShipType.BATTLESHIP,
				grid.shipTypeAt(4, 6));
	}

	// Find ships remaining
	@Test
	public void shipsRemaining() {
		grid = new BattleGridImpl(player);
		assertTrue("No ships initially", !grid.shipsRemaining());
		Ship ship = new ShipImpl(ShipType.SUBMARINE);
		grid.setShipPos(ship, 5, 5, true);
		assertTrue("Ships after placement", grid.shipsRemaining());
		grid.shoot(5, 5);
		grid.shoot(6, 5);
		grid.shoot(7, 5);
		assertTrue("No ships after sunk", !grid.shipsRemaining());
	}

	// Check bounds
	@Test
	public void checkBounds() {
		grid = new BattleGridImpl(player);
		assertTrue("Bounds check low edge", grid.boundsCheck(0, 0));
		assertTrue("Bounds check upper edge", grid.boundsCheck(9, 9));
		assertTrue("Bounds check middle", grid.boundsCheck(5, 5));
		assertTrue("Bounds check outside below", !grid.boundsCheck(0, -1));
		assertTrue("Bounds check outside above", !grid.boundsCheck(0, 11));
	}

	// Check grid size
	@Test
	public void checkGridSize() {
		grid = new BattleGridImpl(player);
		assertEquals("Check grid size", 10, grid.gridSize());
	}

	// Check player
	@Test
	public void checkPlayer() {
		grid = new BattleGridImpl(player);
		assertEquals("Check player", player, grid.getPlayer());
	}

	// Player status
	@Test
	public void checkPlayerStatus() {
		grid = new BattleGridImpl(player);
		grid.setPlayerState(true);
		assertTrue("Active player", grid.getPlayerState());
		grid.setPlayerState(false);
		assertTrue("Active player", !grid.getPlayerState());
	}
}
