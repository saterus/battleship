package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.WaitingController;

public class WinView extends JPanel {

    private final WaitingController controller;
    
    private final JLabel label;
    
    private final static int WIDTH = 200;
    private final static int HEIGHT = 120;
    
    public WinView(WaitingController con, String winMessage) {
        this.controller = con;

        this.setLayout(new GridBagLayout());

        label = new JLabel(winMessage);
        label.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.add(label, new GridBagConstraints());
        
        // todo: add both grids.

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // gracefully exit the game.
            }
        });

    }
}
