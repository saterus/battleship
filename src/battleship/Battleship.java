package battleship;

import views.BattleshipFrame;

/**
 * This class generates a new game of Battleship to be played by two players.
 * 
 * @author Group c421aa06
 */
public final class Battleship {

	/**
	 * Default constructor. Made private to avoid being used.
	 */
	private Battleship() {

	}

	/**
	 * The main function which runs the Battleship game.
	 * 
	 * @param args
	 *            the argument array. Battleship requires no arguments.
	 */
	public static void main(String[] args) {

		BattleshipFrame game = new BattleshipFrame();
		game.start();
		game.setVisible(true);

	}

}
