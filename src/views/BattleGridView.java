package views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.BattleGrid;
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
    private static final long   serialVersionUID = 1L;

    /**
     * A LOGGER for use with the BattleGridView class.
     */
    private static final Logger LOGGER           = Logger.getLogger(BattleGridView.class
                                                         .getName());

    /** Size of the grid used by the game. */
    private final int           gridSize;

    /** Grid gap size in pixels. */
    private static final int    GRID_GAP         = 3;

    /**
     * The dimension for the rigid area.
     */
    private static final int    RIGID_DIM        = 10;

    /**
     * JPanel to be used in setting up the layout.
     */
    private JPanel              container;

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
        this.gridSize = grid.gridSize() + 1;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));
        this.add(new JLabel(grid.getPlayer().getPlayerName()));
        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));

        container = new JPanel();
        container.setLayout(new GridLayout(gridSize, gridSize, GRID_GAP, GRID_GAP));
        this.add(container);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {

                if (i > 0 && j > 0) { // the grid
                    BattleGridSquare square = new BattleGridSquare(j - 1, i - 1);
                    square.addPlacementClickListener(this, con, grid);
                    container.add(square);
                    square.setSquareBackground(grid);

                } else if (i == 0 && j == 0) { // blank spot
                    JPanel blank = new JPanel();
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);
                    container.add(blank);

                } else if (j == 0) { // rows
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf((char) (((int) 'A') + i - 1))),
                            new GridBagConstraints());

                    container.add(blank);
                } else { // column numbers
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf(j)), new GridBagConstraints());

                    container.add(blank);
                }
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
        this.gridSize = targetGrid.gridSize() + 1;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));
        this.add(new JLabel(targetGrid.getPlayer().getPlayerName()));
        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));

        container = new JPanel();
        container.setLayout(new GridLayout(gridSize, gridSize, GRID_GAP, GRID_GAP));
        this.add(container);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {

                if (i > 0 && j > 0) { // the grid
                    BattleGridSquare square = new BattleGridSquare(j - 1, i - 1);
                    square.addFiringClickListener(this, con, targetGrid);
                    container.add(square);
                    square.setSquareBackground(targetGrid);

                } else if (i == 0 && j == 0) { // blank spot
                    JPanel blank = new JPanel();
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);
                    container.add(blank);

                } else if (j == 0) { // rows
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf((char) (((int) 'A') + i - 1))),
                            new GridBagConstraints());

                    container.add(blank);
                } else { // column numbers
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf(j)), new GridBagConstraints());

                    container.add(blank);
                }
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
        this.gridSize = grid.gridSize() + 1;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));
        this.add(new JLabel(grid.getPlayer().getPlayerName()));
        this.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));

        container = new JPanel();
        container.setLayout(new GridLayout(gridSize, gridSize, GRID_GAP, GRID_GAP));
        this.add(container);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (i > 0 && j > 0) { // the grid

                    BattleGridSquare square = new BattleGridSquare(j - 1, i - 1);
                    container.add(square);
                    square.setSquareBackground(grid);

                } else if (i == 0 && j == 0) { // blank spot
                    JPanel blank = new JPanel();
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);
                    container.add(blank);

                } else if (j == 0) { // rows
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf((char) (((int) 'A') + i - 1))),
                            new GridBagConstraints());

                    container.add(blank);
                } else { // column numbers
                    JPanel blank = new JPanel();
                    blank.setLayout(new GridBagLayout());
                    blank.setBackground(BattleGridSquare.LABEL_COLOR);

                    blank.add(new JLabel(String.valueOf(j)), new GridBagConstraints());

                    container.add(blank);
                }
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
            if (square instanceof BattleGridSquare) {
                BattleGridSquare sq = (BattleGridSquare) square;
                sq.setSquareBackground(grid);
            }
        }
        LOGGER.finest("Redrawing Square Backgrounds.");
    }

}
