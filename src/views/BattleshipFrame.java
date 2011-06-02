package views;

import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.BattleGrid;
import models.BattleGridImpl;
import models.Player;
import models.PlayerImpl;
import controllers.FiringController;
import controllers.PlacementController;
import controllers.WaitingController;
import controllers.WaitingControllerImpl;

public final class BattleshipFrame extends JFrame {

    private static final Logger logger = Logger.getLogger(BattleshipFrame.class.getName());

    /**
     * The WaitingController for use with the battleship game.
     */
    private WaitingController   waiting;

    private JPanel              currentView;

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
    private void createPlacementView() {
        PlacementController placing = waiting.switchPlacementPlayer();

        PlacementView view = new PlacementView(placing, waiting.getActiveGrid());

        this.currentView = view;
        this.setContentPane(view);

        logger.finer("Created Placement View");
    }

    private void createWaitingView() {
        WaitingView view = new WaitingView(waiting);

        this.currentView = view;
        this.setContentPane(view);

        logger.finer("Created Waiting View");
    }

    private void createFiringView() {
        FiringController firing = waiting.switchFiringPlayer();

        FiringView view = new FiringView(waiting, firing);

        this.currentView = view;
        this.setContentPane(view);

        logger.finer("Created Firing View");
    }

    public void start() {
        createPlacementView();
    }

    @SuppressWarnings("rawtypes")
    public void switchView(Class nextView) {
        this.currentView.setVisible(false);

        if (WaitingView.class == nextView) {
            createWaitingView();
        } else if (PlacementView.class == nextView) {
            createPlacementView();
        } else if (FiringView.class == nextView) {
            createFiringView();
        } else {
            throw new IllegalArgumentException("Unexpected view class: "
                    + nextView.toString());
        }

    }
}
