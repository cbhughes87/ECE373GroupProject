package store.UI;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import store.objects.GroceryStore;
import store.objects.Product;

public class LargeProductPanel extends JPanel {
	private GroceryStore store;
	private Product prod;
	private SmallProductPanel prodPanel;
	private JButton back, add;
	private JSpinner numToAdd;
	private JTabbedPane bottom;
	
	public LargeProductPanel(GroceryStore store, Product product){
		this.store = store;
		this.prod = product;
		setLayout(new GridBagLayout());
		prodPanel = new SmallProductPanel(prod, 490, 210);
		back = new JButton("<-Back");
		add = new JButton("Add to Cart");
		SpinnerModel model = new SpinnerNumberModel(1.0, 0, prod.getQuantity(), 0.5);
		numToAdd = new JSpinner(model);
		bottom = new JTabbedPane();
		JPanel info = constructInformation();
	}
	
	private JPanel constructInformation(){
		JPanel toReturn = new JPanel();
		
		return toReturn;
	}
}
