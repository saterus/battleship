package controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

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
     * A logger for use with the PlacementControllerImpl class.
     */
    private static final Logger LOGGER         = Logger.getLogger(PlacementControllerImpl.class
                                                       .getName());

    /**
     * The current grid being placed on by the current player. Mutates with
     * every successful placement.
     */
    private BattleGrid          grid;

    /**
     * The current type of Ship to be placed.
     */
    private ShipType            currentShipType;

    /**
     * The current orientation of the Ship to be placed on the grid. True
     * correlates to an orientation along the x-axis, and false correlates to
     * orientation along the y-axis.
     */
    private boolean             curOrientation = true;

    /**
     * The ShipTypes that have not yet been placed.
     */
    private List<ShipType>      shipTypesLeft  = new LinkedList<ShipType>();

    /**
     * The ShipTypeSelector to be used with Ship placement.
     */
    private ShipTypeSelector    selector;

    /**
     * The WaitingController to be used with Ship placement.
     */
    private WaitingController   waiting;

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

        this.currentShipType = shipTypesLeft.get(0);
    }

    @Override
    /**
     * Modifies this.grid by adding the ship to the BattleGrid if a valid placement.
     */
    public boolean setShipPos(int x, int y) {
        // check easy boundary conditions
        if (null == this.currentShipType
                || !this.grid.boundsCheck(x, y)
                || (this.curOrientation && !this.grid.boundsCheck(x
                        + this.currentShipType.length() - 1, y))
                || (!this.curOrientation && !this.grid.boundsCheck(x, y
                        + this.currentShipType.length() - 1))) {

            LOGGER.finest("Attempted out of bounds ship placement.");

            return false;
        }

        boolean validPlacement = true;

        if (this.curOrientation) {
            for (int i = 0; i < this.currentShipType.length(); i++) {
                validPlacement = validPlacement && !this.grid.isShip(x + i, y);
            }
        } else {
            for (int i = 0; i < this.currentShipType.length(); i++) {
                validPlacement = validPlacement && !this.grid.isShip(x, y + i);
            }
        }

        if (validPlacement) {
            this.grid.setShipPos(new ShipImpl(this.currentShipType), x, y,
                    this.curOrientation);
        } else {
            LOGGER.fine("Attempted to place on top of an existing ship.");
        }

        return validPlacement;
    }

    @Override
    public void setSelectedShipType(ShipType type) {
        LOGGER.finest("Set ShipType: " + type + ".");
        this.currentShipType = type;
        this.selector.setActiveButton(this.currentShipType);
    }

    @Override
    public void disableSelectedShipType() {
        LOGGER.fine("Disabling " + this.currentShipType.toString() + ".");

        this.shipTypesLeft.remove(this.currentShipType);
        this.selector.disableShipButton(this.currentShipType);
        this.currentShipType = null;

        if (0 == this.shipTypesLeft.size()) {
            LOGGER.fine("Switching players.");
            this.waiting.nextScreen(null);
        } else {
            this.currentShipType = this.shipTypesLeft.get(0);
            this.selector.setActiveButton(this.currentShipType);
        }
    }

    @Override
    public void registerShipTypeSelector(ShipTypeSelector selector) {
        LOGGER.finest("Registered ShipTypeSelector.");

        this.selector = selector;
    }

    @Override
    public void rotateShip() {
        LOGGER.finest("Rotated Ship Placement: " + this.curOrientation + ".");

        this.curOrientation = !this.curOrientation;

    }

}
