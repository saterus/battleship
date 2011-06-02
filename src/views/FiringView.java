package views;

import javax.swing.JPanel;

import controllers.FiringController;
import controllers.WaitingController;

/**
 * Implements a view for use with Battleship that manages one player firing at
 * the other.
 * 
 * @author Group c421aa06
 */
public final class FiringView extends JPanel {

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which generates a view for one player firing at the other
	 * player's BattleGrid.
	 * 
	 * @param waiting
	 *            the WaitingController to handle transitions between
	 *            FiringViews.
	 * @param firing
	 *            the FiringController to handle one player firing at the other.
	 */
	public FiringView(WaitingController waiting, FiringController firing) {

	}
}
