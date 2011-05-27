package cse421.battleship.controllers;

public interface FiringController {
    
    public boolean fireShot(int x, int y);
    
    public boolean isShootable(int x, int y);

}
