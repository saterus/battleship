package views;

import java.awt.GridLayout;

import javax.swing.JPanel;

import models.BattleGrid;
import models.ShipType;
import controllers.PlacementController;

public class BattleGridView extends JPanel {
    
    /** Grid gap size in pixels. */
    private final static int GRID_GAP = 2;
    
    public BattleGridView(PlacementController con) {
        
        this.setLayout(new GridLayout(BattleGrid.getGridSize(), 
                                      BattleGrid.getGridSize(), 
                                      GRID_GAP, GRID_GAP));
        
        for(int i = 0; i < BattleGrid.getGridSize(); i++) {
            for(int j = 0; j < BattleGrid.getGridSize(); j++) {
                BattleGridSquare square = new BattleGridSquare(i, j);
                square.addPlacementClickListener(con);
                this.add(square);
            }
        }

    }

}
