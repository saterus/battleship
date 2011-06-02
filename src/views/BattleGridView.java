package views;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.logging.Logger;

import javax.swing.JPanel;

import models.BattleGrid;
import controllers.PlacementController;

/**
 * This class implements the BattleGridView, containing the BattleGridSquares
 * for each player.
 * 
 * @author Group c421aa06
 */
public final class BattleGridView extends JPanel {

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A logger for use with the BattleGridView class.
	 */
	private static final Logger logger = Logger.getLogger(BattleGridView.class
			.getName());

	/** Size of the grid used by the game. */
	private final int GRID_SIZE;

	/** Grid gap size in pixels. */
	private static final int GRID_GAP = 3;

	/**
	 * Constructor that initializes the BattleGridView with the given
	 * PlacementController and BattleGrid.
	 * 
	 * @param con
	 *            the PlacementController to be used with the BattleGridView.
	 * @param grid
	 *            the BattleGrid to be used by the BattleGridView.
	 */
	public BattleGridView(PlacementController con, BattleGrid grid) {

		GRID_SIZE = grid.gridSize();

		this.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE, GRID_GAP, GRID_GAP));

		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				BattleGridSquare square = new BattleGridSquare(i, j);
				square.addPlacementClickListener(this, con, grid);
				this.add(square);
				square.setSquareBackground(grid);
			}
		}
		logger.finer("Created BattleGridView");
	}

	/**
	 * Updates the background colors for each aquare in the grid.
	 * 
	 * @param grid
	 *            the grid to be updated.
	 */
	public void redrawSquareBackgrounds(BattleGrid grid) {
		for (Component square : this.getComponents()) {
			BattleGridSquare sq = (BattleGridSquare) square;
			sq.setSquareBackground(grid);
		}
		logger.finest("Redrawing Square Backgrounds.");
	}
}
