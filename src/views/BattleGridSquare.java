package views;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import models.BattleGrid;
import controllers.PlacementController;

public class BattleGridSquare extends JPanel {

    private final int          x;
    private final int          y;

    private static final Color FOG   = Color.LIGHT_GRAY;
    private static final Color HIT   = Color.RED;
    private static final Color WATER = Color.BLUE;
    private static final Color SHIP  = Color.DARK_GRAY;

    public BattleGridSquare(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void addPlacementClickListener(final PlacementController con) {

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                boolean placementSuccess = con.setShipPos(x, y);

                if (placementSuccess) {
                    con.disableSelectedShipType();
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
    }
}
