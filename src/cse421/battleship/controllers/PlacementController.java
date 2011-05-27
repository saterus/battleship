package cse421.battleship.controllers;

import cse421.battleship.models.ShipType;

public interface PlacementController {
    
    public boolean setShipPos(ShipType t, int x, int y, boolean orient);

}
