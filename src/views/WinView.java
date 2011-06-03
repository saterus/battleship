package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.BattleGrid;
import models.Player;
import controllers.WaitingController;

public class WinView extends JPanel {

    private final WaitingController controller;

    private final static int        HGAP  = 0;
    private final static int        VGAP = 10;

    public WinView(WaitingController con, String winMessage) {
        this.controller = con;

        this.setLayout(new BorderLayout(HGAP, VGAP));

        JPanel topContainer = new JPanel();
        topContainer.setLayout(new GridBagLayout());

        topContainer.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel victoryMessage = new JLabel(winMessage);
        topContainer.add(victoryMessage, new GridBagConstraints());

        JPanel bottomContainer = new JPanel();
        bottomContainer.setLayout(new BoxLayout(bottomContainer, BoxLayout.X_AXIS));

        bottomContainer.add(new BattleGridView(this.controller.getActiveGrid()));
        bottomContainer.add(Box.createRigidArea(new Dimension(20, 0)));
        bottomContainer.add(new BattleGridView(this.controller.getInactiveGrid()));
        
        this.add(topContainer, BorderLayout.NORTH);
        this.add(bottomContainer, BorderLayout.CENTER);

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
    }


}
