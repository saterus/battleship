package views;

import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import models.BattleGrid;
import controllers.PlacementController;

public final class PlacementView extends JPanel {
    
	/**
	 * A logger for use with the PlacementView class.
	 */
    private static final Logger logger = Logger.getLogger(PlacementView.class.getName());

    public PlacementView(PlacementController placing, BattleGrid activeGrid) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(new BattleGridView(placing, activeGrid));

        JPanel rightSide = new JPanel();
        this.add(rightSide);
        rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
        rightSide.add(new ShipTypeSelector(placing));
        rightSide.add(new OrientationSelector(placing));
        
        logger.finer("Placing on grid " + activeGrid.toString());
    }


}
