package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import views.BattleshipFrame;

import models.BattleGrid;
import models.BattleGridImpl;
import models.Player;
import models.PlayerImpl;
import models.Ship;
import models.ShipImpl;
import models.ShipType;
import controllers.FiringController;
import controllers.FiringControllerImpl;
import controllers.WaitingController;
import controllers.WaitingControllerImpl;

public class FiringControllerTest {
	private WaitingController wait;
	private BattleshipFrame frame;
	private BattleGrid grid1, grid2;
	private Player player1, player2;
	private FiringController fire;

	@Before
	public void setUp() {
		player1 = new PlayerImpl("Player 1");
		player2 = new PlayerImpl("Player 2");
		grid1 = new BattleGridImpl();
		grid2 = new BattleGridImpl();
		frame = new BattleshipFrame();
		wait = new WaitingControllerImpl(frame, player1, grid1, player2, grid2);
	}

	// Default constructor and getTarget
	@Test
	public void defaultConstructorAndGetTarget() {
		fire = new FiringControllerImpl(wait, grid1);
		assertEquals("Default constructor", grid1, fire.getTarget());
	}

	// Shootability
	@Test
	public void shootability() {
		fire = new FiringControllerImpl(wait, grid1);
		assertTrue("Before shooting", fire.isShootable(5, 5));
		fire.fireShot(5, 5);
		assertTrue("After shooting", !fire.isShootable(5, 5));
	}

	// Firing Shots
	@Test
	public void firingShots() {
		Ship ship = new ShipImpl(ShipType.DESTROYER);
		grid1.setShipPos(ship, 5, 5, true);
		fire = new FiringControllerImpl(wait, grid1);
		Ship.HitStatus status = fire.fireShot(0, 0);
		assertEquals("Missed shot", Ship.HitStatus.MISS, status);
		status = fire.fireShot(5, 5);
		assertEquals("Hit shot", Ship.HitStatus.HIT, status);
		status = fire.fireShot(6, 5);
		assertEquals("Hit shot", Ship.HitStatus.HIT, status);
		status = fire.fireShot(7, 5);
		assertEquals("Sunk shot", Ship.HitStatus.SUNK, status);
	}
}
