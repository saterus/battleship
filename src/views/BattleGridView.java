package views;

import java.awt.GridLayout;

import javax.swing.JPanel;

import models.BattleGrid;
import controllers.PlacementController;

public class BattleGridView extends JPanel {

    /** Size of the grid used by the game. */
    private final int        GRID_SIZE;

    /** Grid gap size in pixels. */
    private final static int GRID_GAP = 2;

    public BattleGridView(PlacementController con, BattleGrid grid) {

        GRID_SIZE = grid.gridSize();

        this.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE, GRID_GAP, GRID_GAP));

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                BattleGridSquare square = new BattleGridSquare(i, j);
                square.addPlacementClickListener(con, grid);
                this.add(square);
                
                square.setSquareBackground(grid);
            }
        }
    }

}
