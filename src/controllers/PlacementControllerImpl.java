package controllers;

import java.util.HashSet;
import java.util.Set;

import models.BattleGrid;
import models.ShipImpl;
import models.ShipType;
import views.ShipTypeSelector;

/**
 * Controls the placement of ships on a BattleGrid by keeping a BattleGrid as a
 * mutable, internal state variable.
 * 
 * @author Group c421aa06
 * 
 */
public final class PlacementControllerImpl implements PlacementController {

	/**
	 * The current grid being placed on by the current player. Mutates with
	 * every successful placement.
	 */
	private BattleGrid grid;

	/**
	 * The current type of Ship to be placed.
	 */
	private ShipType currentShipType;

	/**
	 * The current orientation of the Ship to be placed on the grid. True
	 * correlates to an orientation along the x-axis, and false correlates to
	 * orientation along the y-axis.
	 */
	private boolean curOrientation;

	/**
	 * The ShipTypes that have not yet been placed.
	 */
	private Set<ShipType> shipTypesLeft = new HashSet<ShipType>();

	/**
	 * The ShipTypeSelector to be used with Ship placement.
	 */
	private ShipTypeSelector selector;

	/**
	 * The WaitingController to be used with Ship placement.
	 */
	private WaitingController waiting;

	/**
	 * Sets up the PlacementControllerImpl with a BattleGrid. Legal placements
	 * are added to the grid and illegal placements are not.
	 * 
	 * @param waiting
	 *            the WaitingController used in conjunction with Ship placement.
	 * @param grid
	 *            the BattleGrid to which ships are being added.
	 */
	public PlacementControllerImpl(WaitingController waiting, BattleGrid grid) {
		this.grid = grid;
		this.waiting = waiting;
		// Initialize set of ShipTypes to place
		shipTypesLeft.add(ShipType.AIRCRAFT_CARRIER);
		shipTypesLeft.add(ShipType.BATTLESHIP);
		shipTypesLeft.add(ShipType.DESTROYER);
		shipTypesLeft.add(ShipType.PATROL_BOAT);
		shipTypesLeft.add(ShipType.SUBMARINE);
	}

	@Override
	/**
	 * Modifies this.grid by adding the ship to the BattleGrid if a valid placement.
	 */
	public boolean setShipPos(ShipType t, int x, int y, boolean orient) {
		boolean validPlacement = true;

		if (orient) {
			for (int i = 0; i < t.length(); i++) {
				// TODO: Add bounds checking within Grid and check those bounds
				// here.
				validPlacement = validPlacement && !this.grid.isShip(x + i, y);
			}
		} else {
			for (int i = 0; i < t.length(); i++) {
				validPlacement = validPlacement && !this.grid.isShip(x, y + i);
			}
		}

		if (validPlacement) {
			grid.setShipPos(new ShipImpl(t), x, y, orient);
		}

		return validPlacement;
	}

	@Override
	public void setSelectedShipType(ShipType type) {
		currentShipType = type;
	}

	@Override
	public void disableSelectedShipType() {
		shipTypesLeft.remove(currentShipType);
	}

	@Override
	public void registerShipTypeSelector(ShipTypeSelector selector) {
		this.selector = selector;
	}

	@Override
	public void rotateShip() {
		curOrientation = !curOrientation;

	}

	@Override
	public boolean allPlaced() {
		return (shipTypesLeft.size() == 0);
	}

}
