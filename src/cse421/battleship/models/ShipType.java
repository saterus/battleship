package cse421.battleship.models;

public enum ShipType {
    
    AIRCRAFT_CARRIER(5), BATTLESHIP(4), DESTROYER(3), SUBMARINE(3), PATROL_BOAT(2);
    
    private int length;

    private ShipType(int l) {
      length = l;
    }

    public int length() {
      return length;
    }
    
    // TODO: Replace underscore with space, and capitalize all words.
    @Override
    public String toString() {
        //only capitalize the first letter
        String s = super.toString();
        return s.substring(0, 1) + s.substring(1).toLowerCase();
    }

}
