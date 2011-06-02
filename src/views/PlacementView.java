package views;

import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import models.BattleGrid;
import controllers.PlacementController;

/**
 * Implements a display for use in Battleship, that allows the user the place
 * Ships onto a grid.
 * 
 * @author Group c421aa06
 */
public final class PlacementView extends JPanel {

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * A LOGGER for use with the PlacementView class.
	 */
	private static final Logger LOGGER = Logger.getLogger(PlacementView.class
			.getName());

	/**
	 * Constructor which generates a PlacementView for placing Ships on the
	 * active grid.
	 * 
	 * @param placing
	 *            the PlacementController to be used with the PlacementView.
	 * @param activeGrid
	 *            the BattleGrid to place Ships on.
	 */
	public PlacementView(PlacementController placing, BattleGrid activeGrid) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(new BattleGridView(placing, activeGrid));

		JPanel rightSide = new JPanel();
		this.add(rightSide);
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.add(new ShipTypeSelector(placing));
		rightSide.add(new OrientationSelector(placing));

		LOGGER.finer("Placing on grid " + activeGrid.toString());
	}

}
