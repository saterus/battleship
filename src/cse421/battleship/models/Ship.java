package cse421.battleship.models;

public interface Ship {

    public boolean isSunk();
    
    public void hit();
    
    public ShipType type();
    
}
