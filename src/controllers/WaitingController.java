package controllers;

//TODO do we need a mathmodel and constraint?
//TODO add any constructors as @initially

/**
 * A controller for Player view changing for use in battleship. The user of this
 * interface has control over what causes the view to change.
 * 
 * @author Group c421aa06
 */
public interface WaitingController {

	/**
	 * Switches the game view between the two Players during placement.
	 * 
	 * @return the new controller for placing the next player's pieces.
	 */
	PlacementController switchPlacementPlayer();
	
	/**
     * Switches the game view between the two Players during firing.
     * 
     * @return the new controller with the correct player attacking and defending.
     */
    FiringController switchFiringPlayer();


}
