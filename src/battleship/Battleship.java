package battleship;

import views.BattleshipFrame;

public class Battleship {

    /**
     * @param args
     */
    public static void main(String[] args) {

        BattleshipFrame game = new BattleshipFrame();
        game.createPlacementView();
        game.setVisible(true);

    }

}
