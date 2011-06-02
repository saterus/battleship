package views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.PlacementController;

/**
 * Generates a button that changes the orientation of the Ship to place.
 * 
 * @author Group c421aa06
 */
public class OrientationSelector extends JPanel {

	private static final Logger logger = Logger
			.getLogger(OrientationSelector.class.getName());

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The PlacementController which uses {@code this}.
	 */
	private final PlacementController controller;

	/**
	 * Defines of the current orientation setting where true corresponds to
	 * orientation along the x-axis and false corresponds to orientation along
	 * the y-axis.
	 */
	private boolean orientation;

	private final JLabel label;

	private final JButton button;

	private final String northSouthLabelText = "North/South Placement";
	private final String eastWestLabelText = "East/West Placement";

	private final String northSouthButtonText = "Change to North/South";
	private final String eastWestButtonText = "Change to East/West";

	/**
	 * Constructor which creates an OrientationSelector button.
	 * 
	 * @param con
	 *            the PlacementController which uses {@code this}.
	 */
	public OrientationSelector(PlacementController con) {
		this.controller = con;
		this.orientation = true;

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		label = new JLabel(eastWestLabelText);
		this.add(label);

		button = new JButton(northSouthButtonText);
		this.add(button);

		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				OrientationSelector.this.controller.rotateShip();

				OrientationSelector.this.orientation = 
					!OrientationSelector.this.orientation;

				if (orientation) {
					OrientationSelector.this.button
							.setText(northSouthButtonText);
					OrientationSelector.this.label.setText(eastWestLabelText);
				} else {
					OrientationSelector.this.button.setText(eastWestButtonText);
					OrientationSelector.this.label.setText(northSouthLabelText);
				}

				logger.finest("orientation set to : "
						+ OrientationSelector.this.orientation);
			}
		});

		logger.finer("OrientationSelector created.");

	}
}
