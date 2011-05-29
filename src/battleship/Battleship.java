package battleship;

import models.ShipType;

public class Battleship {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ShipType s = ShipType.AIRCRAFT_CARRIER;
        System.out.println(s);
        System.out.println(s.length());
    }

}
