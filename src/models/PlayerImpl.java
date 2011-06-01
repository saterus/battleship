package models;

/**
 * {@code Player} implemented as a string.
 * 
 * @correspondence the name of the player = {@code name}.
 * @convention the name of the player = {@code name}.
 * @author Group c421aa06
 */
public final class PlayerImpl implements Player {

	/**
	 * The name of the Player.
	 */
	private String name;

	/**
	 * Constructor that sets the Player's name to {@code name}.
	 * 
	 * @param name
	 *            the name to give to the Player.
	 */
	public PlayerImpl(String name) {
		this.name = name;
	}

	@Override
	public String getPlayerName() {
		return name;
	}
}
