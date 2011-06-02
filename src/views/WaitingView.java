package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.WaitingController;

public final class WaitingView extends JPanel {
    
    private static final Logger logger = Logger.getLogger(WaitingView.class.getName());

    private final WaitingController controller;

    private final JLabel            label;

    private final static String     messageSuffix = " click when Ready";

    public WaitingView(WaitingController con) {
        this.controller = con;

        this.setLayout(new GridBagLayout());

        label = new JLabel(this.controller.getActivePlayer().getPlayerName()
                + WaitingView.messageSuffix);
        label.setPreferredSize(new Dimension(200, 100));

        this.add(label, new GridBagConstraints());
        
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                logger.fine("Finished waiting. Next screen.");
                controller.nextScreen();
            }            
        });
        
        logger.finer("Waiting on " + this.controller.getActivePlayer().getPlayerName());
    }
}
