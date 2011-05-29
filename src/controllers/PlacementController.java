package controllers;

import models.ShipType;

public interface PlacementController {

	/**
	 * Places a Ship, starting at the given x,y coordinate.
	 * 
	 * @param t
	 *            the type of the Ship to place.
	 * @param x
	 *            the x-coordinate of the position of the head of the Ship.
	 * @param y
	 *            the y-coordinate of the position of the head of the Ship.
	 * @param orient
	 *            the orientation of the Ship where {@code true} corresponds to
	 *            alignment along the x-axis, and {@code false} corresponds to
	 *            alignment along the y-axis.
	 * @return {@code true} if the placement is a legitimate placement, and the
	 *         Ship is placed, {@code false} if the Ship cannot be placed where
	 *         attempted.
	 */
	boolean setShipPos(ShipType t, int x, int y, boolean orient);

}
