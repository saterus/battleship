package views;

import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.BattleGrid;
import models.BattleGridImpl;
import models.Player;
import models.PlayerImpl;
import controllers.PlacementController;
import controllers.WaitingController;
import controllers.WaitingControllerImpl;

public final class BattleshipFrame extends JFrame {

    private static final Logger logger = Logger.getLogger(BattleshipFrame.class.getName());

    /**
     * The WaitingController for use with the battleship game.
     */
    private WaitingController   waiting;

    public BattleshipFrame() {

        BattleGrid gridA = new BattleGridImpl();
        BattleGrid gridB = new BattleGridImpl();
        Player playerA = new PlayerImpl("Player 1");
        Player playerB = new PlayerImpl("Player 2");

        waiting = new WaitingControllerImpl(this, playerB, gridB, playerA, gridA);

        this.setTitle("Battleship!");
        this.setSize(800, 600);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        logger.finer("Created Frame.");
    }

    /**
     * Creates a view for Ship placement.
     */
    public void createPlacementView() {
        PlacementController placing = waiting.switchPlacementPlayer();
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        content.add(new BattleGridView(placing, waiting.getActiveGrid()));

        JPanel rightSide = new JPanel();
        content.add(rightSide);
        rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
        rightSide.add(new ShipTypeSelector(placing));
        rightSide.add(new OrientationSelector(placing));

        this.setContentPane(content);
        
        logger.finer("Created Placement View");
    }
}
