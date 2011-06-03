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

/**
 * Maintains and displays the current view for the Battleship game.
 * 
 * @author Group c421aa06
 */
public final class BattleshipFrame extends JFrame {

    /**
     * Serial Version ID.
     */
    private static final long   serialVersionUID = 1L;

    /**
     * The height of the BattleShipFrame.
     */
    private static final int    HEIGHT           = 600;

    /**
     * THe width of the BattleShipFrame.
     */
    private static final int    WIDTH            = 800;

    /**
     * A LOGGER for use with the BattleshipFrame class.
     */
    private static final Logger LOGGER           = Logger.getLogger(BattleshipFrame.class
                                                         .getName());

    /**
     * The WaitingController for use with the battleship game.
     */
    private WaitingController   waiting;

    /**
     * The current view being displayed.
     */
    private JPanel              currentView;

    /**
     * Default constructor. Initializes Players, BattleGrids, and
     * WaitingController to kick off Battleship game.
     */
    public BattleshipFrame() {

        Player playerA = new PlayerImpl("Player 1");
        Player playerB = new PlayerImpl("Player 2");
        BattleGrid gridA = new BattleGridImpl(playerA);
        BattleGrid gridB = new BattleGridImpl(playerB);

        waiting = new WaitingControllerImpl(this, playerB, gridB, playerA, gridA);

        this.setTitle("Battleship!");
        this.setSize(WIDTH, HEIGHT);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LOGGER.finer("Created Frame.");
    }

    /**
     * Creates a view for Ship placement.
     */
    private void createPlacementView() {
        PlacementController placing = waiting.switchPlacementPlayer();

        PlacementView view = new PlacementView(placing, waiting.getActiveGrid());

        this.currentView = view;
        this.setContentPane(view);

        LOGGER.finer("Created Placement View");
    }

    /**
     * Creates a view for transitioning between Players.
     */
    private void createWaitingView(String msg) {
        WaitingView view = new WaitingView(waiting, msg);

        this.currentView = view;
        this.setContentPane(view);

        LOGGER.finer("Created Waiting View");
    }
    
    /**
     * Creates a view for transitioning between Players.
     */
    private void createWinView(String msg) {
        WinView view = new WinView(waiting, msg);

        this.currentView = view;
        this.setContentPane(view);

        LOGGER.finer("Created Win View");
    }

    /**
     * Creates a view for firing on opponent's grid.
     */
    private void createFiringView() {
        FiringController firing = waiting.switchFiringPlayer();

        FiringView view = new FiringView(waiting, firing);

        this.currentView = view;
        this.setContentPane(view);

        LOGGER.finer("Created Firing View");
    }

    /**
     * Begins a new game by creating the PlacementView.
     */
    public void start() {
        createPlacementView();
    }

    /**
     * Switches the current view to nextView.
     * 
     * @param nextView
     *            the view to be switched to.
     */
    @SuppressWarnings("rawtypes")
    public void switchView(Class nextView, String message) {
        this.currentView.setVisible(false);

        if (WaitingView.class == nextView) {
            createWaitingView(message);
            
        } else if (PlacementView.class == nextView) {
            createPlacementView();
            
        } else if (FiringView.class == nextView) {
            createFiringView();
            
        } else if (WinView.class == nextView) {
            createWinView(message);
            
        } else {
            throw new IllegalArgumentException("Unexpected view class: "
                    + nextView.toString());
        }

    }
}
