package views;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.ShipType;
import controllers.PlacementController;

/**
 * This will create the JPanel for the ship selector when placing ships on the
 * Battleship grid.
 * 
 * @author Group c421aa06
 */
public final class ShipTypeSelector extends JPanel {

	/**
	 * A logger for use with the ShipTypeSelector class.
	 */
	private static final Logger logger = Logger
			.getLogger(ShipTypeSelector.class.getName());

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * buttonMap is a map containing a button corresponding to each ShipType.
	 */
	private Map<ShipType, JButton> buttonMap;

	/**
	 * This constructor creates the basic JPanel for the grid during the set-up
	 * phase, containing the initial ships.
	 * 
	 * @param pc
	 *            The placement controller of a certain player.
	 */
	public ShipTypeSelector(PlacementController pc) {
		pc.registerShipTypeSelector(this);
		buttonMap = new HashMap<ShipType, JButton>();

		this.add(new JLabel("Ships"));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		for (ShipType s : ShipType.values()) {
			JButton ship = new JButton(s.toString());
			ship.addMouseListener(new MouseSelectShipType(pc, s));
			this.add(ship);
			buttonMap.put(s, ship);
		}

		logger.finest("Created ShipTypeSeletor.");
	}

	/**
	 * This method removes the capacity to select the button corresponding to a
	 * particular ship after it has been placed.
	 * 
	 * @param s
	 *            A ship that has already been placed and is now being disabled.
	 */

	public void disableShipButton(ShipType s) {
		buttonMap.get(s).setEnabled(false);
		logger.finer("Disabled " + s.toString() + " Button");
	}
}
