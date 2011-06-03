package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import models.BattleGrid;
import models.BattleGridImpl;
import models.Player;
import models.PlayerImpl;

import org.junit.Before;
import org.junit.Test;

import views.BattleshipFrame;
import controllers.PlacementController;
import controllers.PlacementControllerImpl;
import controllers.WaitingController;
import controllers.WaitingControllerImpl;

public class WaitingControllerTest {
	private WaitingController wait;
	private BattleshipFrame frame;
	private BattleGrid grid1, grid2;
	private Player player1, player2;

	@Before
	public void setUp() {
		player1 = new PlayerImpl("Player 1");
		player2 = new PlayerImpl("Player 2");
		grid1 = new BattleGridImpl();
		grid2 = new BattleGridImpl();
		frame = new BattleshipFrame();
		wait = new WaitingControllerImpl(frame, player1, grid1, player2, grid2);
	}

	// Constructor
	@Test
	public void constructor() {
		assertEquals("Active player", player1, wait.getActivePlayer());
		assertEquals("Active grid", grid1, wait.getActiveGrid());
		assertEquals("Inactive player", player2, wait.getInactivePlayer());
		assertEquals("Inactive grid", grid2, wait.getInactiveGrid());
	}

	// Switch placement
	@Test
	public void switchPlacement() {
		wait.switchPlacementPlayer();
		assertEquals("Active player", player2, wait.getActivePlayer());
		assertEquals("Active grid", grid2, wait.getActiveGrid());
		assertEquals("Inactive player", player1, wait.getInactivePlayer());
		assertEquals("Inactive grid", grid1, wait.getInactiveGrid());
	}

	// Switch firing
	@Test
	public void switchFiring() {
		wait.switchFiringPlayer();
		assertEquals("Active player", player2, wait.getActivePlayer());
		assertEquals("Active grid", grid2, wait.getActiveGrid());
		assertEquals("Inactive player", player1, wait.getInactivePlayer());
		assertEquals("Inactive grid", grid1, wait.getInactiveGrid());
	}
}
