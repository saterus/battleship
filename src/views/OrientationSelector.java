package views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import controllers.PlacementController;

public class OrientationSelector extends JButton {

	private PlacementController controller;

	/**
	 * Follows same convention as the other components, true = x-axis, false =
	 * y-axis.
	 */
	private boolean orientation;

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
