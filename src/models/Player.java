package models;

/**
 * A player for use in battleship. The user of this interface has control over
 * how the player's name is stored.
 * 
 * @mathmodel a string.
 * @constraint the player's name is the string.
 * @initially <br>
 *            constructor(String name): {@code ensures} the player's name is set
 *            to {@code name}.
 * @author Group c421aa06
 */
public interface Player {

	/**
	 * Retrieves the name of the Player.
	 * 
	 * @return the name of the Player.
	 */
	String getPlayerName();

}
