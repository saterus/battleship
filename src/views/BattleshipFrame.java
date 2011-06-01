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

	public BattleshipFrame() {

		BattleGrid gridA = new BattleGridImpl();
		BattleGrid gridB = new BattleGridImpl();
		Player playerA = new PlayerImpl("Player 1");
		Player playerB = new PlayerImpl("Player 2");

		WaitingController waiting = new WaitingControllerImpl(playerB, gridB,
				playerA, gridA);

		PlacementController placing = waiting.switchPlacementPlayer();

		this.pack();
		this.setTitle("Battleship!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Creates a view for Ship placement.
	 */
	private void createPlacementView() {
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		content.add(new GridView());
		content.add(new ShipTypeSelector());
		this.setContentPane(content);
	}
}
