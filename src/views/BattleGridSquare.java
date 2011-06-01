package views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controllers.PlacementController;

public class BattleGridSquare extends JPanel {
    
    private final int x;
    private final int y;
    
    public BattleGridSquare(int x, int y) {
        this.x = x;
        this.y = y;
        
    }
    
    public void addPlacementClickListener(PlacementController con) {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                con.setShipPos(x, y);
                // do more stuff
            }
        });
    }

}
