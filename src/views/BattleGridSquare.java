package views;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.JPanel;

import models.BattleGrid;
import controllers.PlacementController;

public class BattleGridSquare extends JPanel {

	/**
	 * A logger for the BattleGridSquare class.
	 */
	private static final Logger logger = Logger
			.getLogger(BattleGridSquare.class.getName());

    /**
     * The x-position of the grid square in the grid starting from the left.
     */
    private final int           x;

    /**
     * The y-position of the grid square in the grid starting from the top.
     */
    private final int           y;

    /**
     * Color of grid squares that have not been shot on the opponent's grid.
     */
    private static final Color  FOG    = Color.LIGHT_GRAY;

    /**
     * Color of grid squares that hold a Ship and have been shot.
     */
    private static final Color  HIT    = Color.RED;

    /**
     * Color of grid squares that have been shot and hold no Ship.
     */
    private static final Color  WATER  = Color.BLUE;

    /**
     * Color of grid squares that hold a Ship that have not been shot on the
     * player's own grid.
     */
    private static final Color  SHIP   = Color.DARK_GRAY;

    /**
     * Constructor that defines the position of the BattleGridSquare to be x,y.
     * 
     * @param x
     *            the x-coordinate of the BattleGridSquare, starting from the
     *            left.
     * @param y
     *            the y-coordinate of the BattleGridSquare, starting from the
     *            top.
     */
    public BattleGridSquare(int x, int y) {
        this.x = x;
        this.y = y;

        logger.finest("Created square (" + x + "," + y + ").");
    }

    public void addPlacementClickListener(final BattleGridView gView,
            final PlacementController con, final BattleGrid grid) {

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                boolean placementSuccess = con.setShipPos(x, y);
                
                logger.finer("Successful placed ship at (" + x + "," + y + ")?"
                        + placementSuccess);

                if (placementSuccess) {                    
                    con.disableSelectedShipType();
                    gView.redrawSquareBackgrounds(grid);
                }
            }
        });
    }

    public void setSquareBackground(BattleGrid grid) {
        Color currentColor;

        if (grid.getPlayerState()) { // active player owns this grid.

            if (grid.isShip(this.x, this.y)) {
                currentColor = BattleGridSquare.SHIP;
            } else {
                currentColor = BattleGridSquare.WATER;
            }

        } else if (grid.isViewable(this.x, this.y)) {

            if (grid.isShip(this.x, this.y)) {
                currentColor = BattleGridSquare.HIT;
            } else {
                currentColor = BattleGridSquare.WATER;
            }

        } else {
            currentColor = BattleGridSquare.FOG;
        }

        this.setBackground(currentColor);

		logger.finer("Set bg color at (" + x + "," + y + "):"
				+ currentColor.toString());
    }
}
