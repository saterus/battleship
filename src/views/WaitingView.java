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

/**
 * Implements a view for use with Battleship that manages the transitions
 * between players' turns.
 * 
 * @author Group c421aa06
 */
public final class WaitingView extends JPanel {

	/**
	 * A logger for use with the WaitingView class.
	 */
	private static final Logger logger = Logger.getLogger(WaitingView.class
			.getName());

	/**
	 * The end of the message displayed on the screen by the WaitingView.
	 */
	private static final String MESSAGE_SUFFIX = " click when Ready";

	/**
	 * The width of the label used.
	 */
	private static final int WIDTH = 200;

	/**
	 * The height of the label used.
	 */
	private static final int HEIGHT = 100;

	/**
	 * The WaitingController used to control the WaitingView.
	 */
	private final WaitingController controller;

	/**
	 * A JLabel object to be used by the WaitingView.
	 */
	private final JLabel label;

	/**
	 * Default constructor. Sets up the WaitingView.
	 * 
	 * @param con
	 *            the WaitingController for use with this WaitingView.
	 */
	public WaitingView(WaitingController con) {
		this.controller = con;

		this.setLayout(new GridBagLayout());

		label = new JLabel(this.controller.getActivePlayer().getPlayerName()
				+ WaitingView.MESSAGE_SUFFIX);
		label.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		this.add(label, new GridBagConstraints());

		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				logger.fine("Finished waiting. Next screen.");
				controller.nextScreen();
			}
		});

		logger.finer("Waiting on "
				+ this.controller.getActivePlayer().getPlayerName());
	}
}
