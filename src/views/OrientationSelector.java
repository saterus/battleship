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

	/**
	 * A LOGGER for use with the OrientationSelector class.
	 */
	private static final Logger LOGGER = Logger
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

	/**
	 * The label for the OrientationSelector button.
	 */
	private final JLabel label;

	/**
	 * The button to change the orientation.
	 */
	private final JButton button;

	/**
	 * Label text indicating orientation along the y-axis.
	 */
	private final String northSouthLabelText = "North/South Placement";
	
	/**
	 * Label text indicating orientation along the x-axis.
	 */
	private final String eastWestLabelText = "East/West Placement";

	/**
	 * Button text to change orientation to align with y-axis.
	 */
	private final String northSouthButtonText = "Change to North/South";
	
	/**
	 * Button text to change orientation to align with x-axis.
	 */
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

				LOGGER.finest("orientation set to : "
						+ OrientationSelector.this.orientation);
			}
		});

		LOGGER.finer("OrientationSelector created.");

	}
}
