package store.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import store.objects.GroceryStore;
import store.objects.Product;

public class AdminProductPanel extends JPanel {
	private static final int topBottomSpacing = 200;
	
	private GroceryStore store;
	private JLabel productMan;
	private JButton create, remove, edit;
	
	private ArrayList<ActionListener> actionListeners;
	
	public AdminProductPanel(GroceryStore gstore){
		store = gstore;
		productMan = new JLabel("Product Management");
		create = new JButton("Create Product");
		remove = new JButton("Remove Product");
		edit = new JButton("Edit Product");
		
		boolean enable = store.getCurrUser().getPerms().getPermission("inventory");
		create.setEnabled(enable);
		remove.setEnabled(enable);
		edit.setEnabled(enable);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(topBottomSpacing));
		add(productMan);
		add(Box.createGlue());
		add(create);
		add(remove);
		add(edit);
		add(Box.createGlue());
		add(Box.createVerticalStrut(topBottomSpacing));
	}
	
	public void addActionListener(ActionListener a){
		actionListeners.add(a);
	}
	
	public boolean removeActionListener(ActionListener a){
		return actionListeners.remove(a);
	}
	
	private class ProductChangeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if(source.equals(create)){
				createProduct();
			}
			else if(source.equals(remove)){
				removeProduct();
			}
			else if(source.equals(edit)){
				editProduct();
			}
		}

		private void editProduct() {
			Product toEdit = getProduct("Edit");
			for(ActionListener a : actionListeners){
				a.actionPerformed(new ActionEvent(toEdit, ActionEvent.ACTION_PERFORMED, "editproduct"));
			}
		}

		private void removeProduct() {
			Product toEdit = getProduct("Remove");
			toEdit.getDepartment().getInventory().removeProduct(toEdit);
		}

		private void createProduct() {
			for(ActionListener a : actionListeners){
				a.actionPerformed(new ActionEvent(null, ActionEvent.ACTION_PERFORMED, "createproduct"));
			}
		}
		
		private Product getProduct(String custom){
			Product[] prods = store.getAllProducts();
			Object[] strs = new Object[prods.length];
			for(int i = 0; i < prods.length; i++){
				strs[i] = prods.toString();
			}
			String choice = (String)JOptionPane.showInputDialog(getParent(), 
					"Choose a product to " + custom.toLowerCase(), 
					custom + " Product", 
					JOptionPane.PLAIN_MESSAGE, null, strs, strs[0]);
			Product toEdit = null;
			for(Product p : prods){
				if(p.toString().equals(choice)){
					toEdit = p;
					break;
				}
			}
			return toEdit;
		}
	}
}
