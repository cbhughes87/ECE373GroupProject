package store.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import store.objects.Department;
import store.objects.GroceryStore;
import store.objects.Inventory;
import store.objects.Product;

public class StockPanel extends JPanel {
	private static final int topBottomSpacing = 200;
	
	private GroceryStore store;
	private JLabel productMan;
	private JScrollPane scrollBar;
	private Product dummy;
	private JButton back;
	private ArrayList<ActionListener> actionListeners;
	
	public StockPanel(GroceryStore gstore) {
		store = gstore;
		dummy = new Product();
		dummy.setIDNum(-1);
		actionListeners = new ArrayList<ActionListener>();
		Department tempDept; 
		Inventory tempInv;
		ArrayList<Product> tempProds;
		Product tempProd;
		JList list;
	    DefaultListModel listModel;
	    listModel = new DefaultListModel();
		ArrayList<String> words;
		scrollBar = new JScrollPane(this,
	            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		back = new JButton("<- Back");
		back.setAlignmentX(CENTER_ALIGNMENT);
		back.addActionListener(new StockChangeListener());

		for (int i = 0; i < store.getDepartments().size(); i = i+1) {
			tempDept = store.getDepartments().get(i);
			tempInv = tempDept.getInventory();
			tempProds = tempInv.getProducts();
			for (int j = 0; j < tempProds.size(); j = j+1) {
				tempProd = tempProds.get(j);
				listModel.addElement(tempProd.getName() + ": " + Double.toString(tempProd.getQuantity()) + " in stock");
			}
			list = new JList(listModel);
			scrollBar = new JScrollPane(list);
		}
		add(Box.createVerticalStrut(topBottomSpacing));
		add(scrollBar);
		add(back);
		add(Box.createGlue());
		add(Box.createVerticalStrut(topBottomSpacing));
	}
	public void addActionListener(ActionListener a){
		actionListeners.add(a);
	}
	
	private class StockChangeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if(source.equals(back)){
				for(ActionListener a : actionListeners){
					a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "back"));
				}
			}
		}
	}
}
