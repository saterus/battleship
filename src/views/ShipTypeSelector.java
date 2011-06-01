package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.PlacementController;

public class ShipTypeSelector extends JPanel {

	private JButton carrier = new JButton("Aircraft Carrier");
	private JButton battleship = new JButton("Battleship");
	private JButton destroyer = new JButton("Destroyer");
	private JButton sub = new JButton("Submarine");
	private JButton patrol = new JButton("Patrol Boat");
	
	
	public ShipTypeSelector(PlacementController pc) {
		pc.registerShipTypeSelector(this);
		
		JPanel shipList = new JPanel();
		
		shipList.add(new JLabel("Ships"));
		
		
		
		
	}
	
	
}
