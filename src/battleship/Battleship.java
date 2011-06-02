package battleship;

import views.BattleshipFrame;

public class Battleship {

    /**
     * @param args
     */
    public static void main(String[] args) {

        BattleshipFrame game = new BattleshipFrame();
        game.start();
        game.setVisible(true);

    }

}
