package cse421.battleship.models;

public final class PlayerImpl implements Player {

	/**
	 * The name of the Player.
	 */
	private String name;

	/**
	 * Default constructor. Sets the Player's name to a default value of
	 * "New Player".
	 */
	public PlayerImpl() {
		name = "New Player";
	}

	/**
	 * Constructor that sets the Player's name to {@code name}.
	 * @param name the name to give to the Player.
	 */
	public PlayerImpl(String name) {
		this.name = name;
	}

	@Override
	public String getPlayerName() {
		return name;
	}
}
