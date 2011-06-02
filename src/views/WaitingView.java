package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.WaitingController;

public final class WaitingView extends JPanel {

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
                controller.nextScreen();
            }            
        });
    }
}
