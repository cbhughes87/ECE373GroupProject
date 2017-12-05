package store.UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import store.objects.GroceryStore;

public class AdminProductPanel extends JPanel {
	private GroceryStore store;
	private JLabel productMan;
	private JButton create, remove, edit;
	
	public AdminProductPanel(GroceryStore gstore){
		store = gstore;
		productMan = new JLabel("Product Management");
		create = new JButton("Create Product");
		remove = new JButton("Remove Product");
		edit = new JButton("Edit Product");
		create.setEnabled(store.getCurrUser().getPerms().getPermission(""));
	}
}
