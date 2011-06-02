package views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import models.ShipType;
import controllers.PlacementController;

/**
 * Defines the activity when a mouse is clicked while selecting ships to be
 * placed.
 * 
 * @author Group c421aa06
 */
public final class MouseSelectShipType extends MouseAdapter {

	/**
	 * A player's placement controller when a ship is being placed.
	 */
	private PlacementController pl;

	/**
	 * The particular type of ship being placed, as defined by ShipType.
	 */

	private ShipType t;

	/**
	 * The first constructor which specifies the specific placement controller
	 * and ShipType.
	 * 
	 * @param pc
	 *            The player's placement controller.
	 * @param s
	 *            The particular ship type being dealt with.
	 */
	public MouseSelectShipType(PlacementController pc, ShipType s) {
		this.pl = pc;
		this.t = s;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.pl.setSelectedShipType(this.t);
	}
}
