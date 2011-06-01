package views;

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

public class BattleshipFrame extends JFrame {

	/**
	 * The WaitingController for use with the battleship game.
	 */
	private WaitingController waiting;

	public BattleshipFrame() {

		BattleGrid gridA = new BattleGridImpl();
		BattleGrid gridB = new BattleGridImpl();
		Player playerA = new PlayerImpl("Player 1");
		Player playerB = new PlayerImpl("Player 2");

		waiting = new WaitingControllerImpl(this, playerB, gridB, playerA, gridA);

		this.pack();
		this.setTitle("Battleship!");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Creates a view for Ship placement.
	 */
	public void createPlacementView() {
		PlacementController placing = waiting.switchPlacementPlayer();
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		content.add(new BattleGridView(placing, waiting.getActiveGrid()));
		content.add(new ShipTypeSelector(placing));
		this.setContentPane(content);
	}
}
