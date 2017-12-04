package store.UI;

import javax.swing.JPanel;

import store.objects.GroceryStore;

public class CheckoutPanel extends JPanel {
	private GroceryStore store;
	
	public CheckoutPanel(GroceryStore store){
		this.store = store;
	}
}
