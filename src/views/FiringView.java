package views;

import java.util.logging.Logger;

import javax.swing.BoxLayout;
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
     * A LOGGER for use with the FiringView class.
     */
    private static final Logger LOGGER = Logger.getLogger(FiringView.class.getName());

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
	    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.add(new BattleGridView(firing));
	    this.add(new BattleGridView(waiting.getActiveGrid()));
	    
	    LOGGER.finer("Created Firing View.");
	}
}
