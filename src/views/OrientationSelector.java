package views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import controllers.PlacementController;

/**
 * Generates a button that changes the orientation of the Ship to place.
 * 
 * @author Group c421aa06
 */
public class OrientationSelector extends JButton {

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The PlacementController which uses {@code this}.
	 */
	private PlacementController controller;

	/**
	 * Defines of the current orientation setting where true corresponds to
	 * orientation along the x-axis and false corresponds to orientation along
	 * the y-axis.
	 */
	private boolean orientation;

	/**
	 * Constructor which creates an OrientationSelector button.
	 * 
	 * @param con the PlacementController which uses {@code this}.
	 */
	public OrientationSelector(PlacementController con) {
		this.controller = con;
		this.orientation = true;
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				OrientationSelector.this.orientation = 
					!OrientationSelector.this.orientation;
				OrientationSelector.this.controller.rotateShip();
			}
		});

		if (orientation) {
			this.setText("Change to vertical");
		} else {
			this.setText("Change to horizontal.");
		}
	}
}
