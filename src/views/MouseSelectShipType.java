package views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import models.ShipType;
import controllers.PlacementController;

/**
 * 
 * Defines the activity when a mouse is clicked while selecting
 * 	ships to be placed.
 * 
 */
public class MouseSelectShipType extends MouseAdapter {
	
	/**
	 * A player's placement controller when a ship is
	 * 	being placed.
	 * 
	 */
	private final PlacementController pl;
	
	/**
	 * The particular type of ship being placed, as
	 * 	defined by ShipType.
	 */
	
	private final ShipType t;
	
	/**
	 * The first constructor which specifies the specific
	 * 	placement controller and ShipType.
	 * 
	 * @param pc	
	 * 				The player's placement controller.
	 * @param s
	 * 				The particular ship type being dealt with.
	 */
	
	public MouseSelectShipType(PlacementController pc,
							ShipType s) {
		
		this.pl = pc;
		this.t = s;
		
	}
	

	@Override
	public final void mouseClicked(MouseEvent e) {
        	
		this.pl.setSelectedShipType(this.t);
	}
}


