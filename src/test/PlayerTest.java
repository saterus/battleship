package test;

import static org.junit.Assert.assertEquals;

import models.Player;
import models.PlayerImpl;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	private Player player;

	@Before
	public void setUp() {

	}

	// Empty name
	@Test
	public void defaultConstructorEmpty() {
		player = new PlayerImpl("");
		assertEquals("Empty name", "", player.getPlayerName());
	}

	// No spaces
	@Test
	public void defaultConstructorNoSpaces() {
		player = new PlayerImpl("John");
		assertEquals("No spaces", "John", player.getPlayerName());
	}

	// With spaces
	@Test
	public void defaultConstructorWithSpaces() {
		player = new PlayerImpl("John Smith");
		assertEquals("With spaces", "John Smith", player.getPlayerName());
	}

	// Only spaces
	@Test
	public void defaultConstructorOnlySpaces() {
		player = new PlayerImpl("   ");
		assertEquals("Only spaces", "   ", player.getPlayerName());
	}

	// Long name
	@Test
	public void defaultConstructorLongName() {
		player = new PlayerImpl(
				"aklgh;qjvn;ona;nevec;oarw;odshoichOEFHOihsd;vn;ocnINOco");
		assertEquals("Only spaces",
				"aklgh;qjvn;ona;nevec;oarw;odshoichOEFHOihsd;vn;ocnINOco",
				player.getPlayerName());
	}
}
