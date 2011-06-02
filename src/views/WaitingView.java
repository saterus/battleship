package views;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.WaitingController;

public class WaitingView extends JPanel {

    private WaitingController   controller;

    private final static String messageSuffix = "click when Ready";

    public WaitingView(WaitingController con) {
        this.controller = con;

        this.add(new JLabel(this.controller.getActivePlayer() + WaitingView.messageSuffix));
    }
}
