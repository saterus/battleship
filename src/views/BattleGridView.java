package views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.BattleGrid;
import models.Player;
import controllers.FiringController;
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
	 * A LOGGER for use with the BattleGridView class.
	 */
	private static final Logger LOGGER = Logger.getLogger(BattleGridView.class
			.getName());

	/** Size of the grid used by the game. */
	private final int gridSize;

	/** Grid gap size in pixels. */
	private static final int GRID_GAP = 3;
	
	private JPanel container;

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
        this.gridSize = grid.gridSize();
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(new JLabel(grid.getPlayer().getPlayerName()));
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        container = new JPanel();
        container.setLayout(new GridLayout(gridSize, gridSize, GRID_GAP, GRID_GAP));
        this.add(container);
        
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				BattleGridSquare square = new BattleGridSquare(i, j);
				square.addPlacementClickListener(this, con, grid);
				container.add(square);
				square.setSquareBackground(grid);
			}
		}

		LOGGER.finer("Created BattleGridView for Placement");
	}

	/**
	 * Constructor that initializes the BattleGridView with the given
	 * FiringController.
	 * 
	 * @param con
	 *            the FiringController to be used with the BattleGridView.
	 */
	public BattleGridView(FiringController con) {
		BattleGrid targetGrid = con.getTarget();
		this.gridSize = targetGrid.gridSize();
  
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(new JLabel(targetGrid.getPlayer().getPlayerName()));
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        container = new JPanel();
        container.setLayout(new GridLayout(gridSize, gridSize, GRID_GAP, GRID_GAP));
        this.add(container);

		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				BattleGridSquare square = new BattleGridSquare(i, j);
				square.addFiringClickListener(this, con, targetGrid);
				container.add(square);
				square.setSquareBackground(targetGrid);
			}
		}

		LOGGER.finer("Created BattleGridView for Firing");
	}

	/**
	 * Constructor which initializes the BattleGridView with the given
	 * BattleGrid.
	 * 
	 * @param grid
	 *            the BattleGrid to be used by the BattleGridView.
	 */
	public BattleGridView(BattleGrid grid) {
		this.gridSize = grid.gridSize();
		
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(new JLabel(grid.getPlayer().getPlayerName()));
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        container = new JPanel();
		container.setLayout(new GridLayout(gridSize, gridSize, GRID_GAP, GRID_GAP));
		this.add(container);

		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				BattleGridSquare square = new BattleGridSquare(i, j);
				container.add(square);
				square.setSquareBackground(grid);
			}
		}

		LOGGER.finer("Created BattleGridView for Viewing");
	}
	
    
        
        


	/**
	 * Updates the background colors for each square in the grid.
	 * 
	 * @param grid
	 *            the grid to be updated.
	 */
	public void redrawSquareBackgrounds(BattleGrid grid) {
		for (Component square : this.container.getComponents()) {
			BattleGridSquare sq = (BattleGridSquare) square;
			sq.setSquareBackground(grid);
		}
		LOGGER.finest("Redrawing Square Backgrounds.");
	}
	
	
}
