package cse421.battleship.models;

public interface BattleGrid {

    public void setShipPos(Ship ship, int x, int y, boolean orient);
    
    public boolean shoot(int x, int y);
    
    public boolean isViewable(int x, int y);
    
    public boolean isShip(int x, int y);
    
    //public boolean isRevealed(int x, int y); Removed because it leaks information.
}
