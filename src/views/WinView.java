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

import controllers.WaitingController;

/**
 * Implements a view for use with Battleship that handles the case where a
 * player wins the game.
 * 
 * @author Group c421aa06
 */
public final class WinView extends JPanel {

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The WaitingController which manages the WinView.
	 */
	private WaitingController controller;

	/**
	 * The dimension for the rigid area.
	 */
	private static final int RIGID_DIM = 20;

	/**
	 * The HGAP for the border layout.
	 */
	private static final int HGAP = 0;

	/**
	 * The VGAP for the border layout.
	 */
	private static final int VGAP = 10;

	/**
	 * Constructor which generates the WinView, initialized with the given
	 * WaitingController and win message.
	 * 
	 * @param con
	 *            the WaitingController used to manage the WinView.
	 * @param winMessage
	 *            the win message to be displayed by the WinView.
	 */
	public WinView(WaitingController con, String winMessage) {
		this.controller = con;

		this.setLayout(new BorderLayout(HGAP, VGAP));

		JPanel topContainer = new JPanel();
		topContainer.setLayout(new GridBagLayout());

		topContainer.add(Box.createRigidArea(new Dimension(0, RIGID_DIM)));

		JLabel victoryMessage = new JLabel(winMessage);
		topContainer.add(victoryMessage, new GridBagConstraints());

		JPanel bottomContainer = new JPanel();
		bottomContainer.setLayout(new BoxLayout(bottomContainer,
				BoxLayout.X_AXIS));

		bottomContainer
				.add(new BattleGridView(this.controller.getActiveGrid()));
		bottomContainer.add(Box.createRigidArea(new Dimension(RIGID_DIM, 0)));
		bottomContainer.add(new BattleGridView(this.controller
				.getInactiveGrid()));

		this.add(topContainer, BorderLayout.NORTH);
		this.add(bottomContainer, BorderLayout.CENTER);

		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}

}
