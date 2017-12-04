package store.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import store.objects.GroceryStore;
import store.objects.Product;
import store.objects.States;
import store.software.ShoppingCart;

public class CheckoutPanel extends JPanel {
	private GroceryStore store;
	
	private JButton back, order;
	private JLabel payinfo, checkout, first, last, cardnumber, cvc, billAddr, city, state, orderconfirmed;
	private JTextField firstField, lastField, cardnumField, cvcField, billAddrField1, billAddrField2, cityField;
	private JComboBox<String> stateDropDown;
	private JPanel cartItems;
	
	private ArrayList<ActionListener> actionListeners;
	
	public CheckoutPanel(GroceryStore gstore){
		this.store = gstore;
		actionListeners = new ArrayList<ActionListener>();
		setLayout(new GridBagLayout());
		back = new JButton("<- Back");
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for(ActionListener a : actionListeners){
					a.actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "back"));
				}
			}
		});
		order = new JButton("Place Order");
		order.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				store.getCurrUser().removeAll("Purchased items.");
				for(ActionListener a : actionListeners){
					a.actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "order"));
				}
			}
		});
		
		payinfo = new JLabel("Payment Information");
		checkout = new JLabel("Check Out");
		first = new JLabel("First Name");
		last = new JLabel("Last Name");
		cardnumber = new JLabel("Card Number");
		cvc = new JLabel("CVV");
		billAddr = new JLabel("Billing Address");
		city = new JLabel("City");
		state = new JLabel("State");
		orderconfirmed = new JLabel("");
		orderconfirmed.setHorizontalAlignment(JLabel.CENTER);
		
		firstField = new JTextField();
		lastField = new JTextField();
		cardnumField = new JTextField();
		cvcField = new JTextField();
		billAddrField1 = new JTextField();
		billAddrField2 = new JTextField();
		cityField = new JTextField();
		
		stateDropDown = new JComboBox<String>();
		String[] sorted = States.getStateCodes().toArray(new String[]{""});
		Arrays.sort(sorted);
		for(String s : sorted){
			stateDropDown.addItem(s);
		}
		
		cartItems = createCartItems();
		cartItems.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.black));
		
		layoutComponents();
	}
	
	public void addActionListener(ActionListener a){
		actionListeners.add(a);
	}
	
	public boolean removeActionListener(ActionListener a){
		return actionListeners.remove(a);
	}
	
	private void layoutComponents(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0;
		c.weighty = 0;
		add(back, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(checkout, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(payinfo, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(first, c);
		
		c.gridx = 2;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(last, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(firstField, c);
		
		c.gridx = 2;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0.01;
		add(lastField, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0.01;
		add(cardnumber, c);
		
		c.gridx = 2;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(cvc, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(cardnumField, c);
		
		c.gridx = 2;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(cvcField, c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(billAddr, c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(billAddrField1, c);
		
		c.gridx = 0;
		c.gridy = 8;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(billAddrField2, c);
		
		c.gridx = 0;
		c.gridy = 9;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(city, c);
		
		c.gridx = 2;
		c.gridy = 9;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(state, c);
		
		c.gridx = 0;
		c.gridy = 10;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(cityField, c);
		
		c.gridx = 2;
		c.gridy = 10;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(stateDropDown, c);
		
		c.gridx = 1;
		c.gridy = 11;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(order, c);
		
		c.gridx = 1;
		c.gridy = 12;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 0;
		add(orderconfirmed, c);
		
		c.gridx = 0;
		c.gridy = 13;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.1;
		c.weighty = 1;
		add(new JLabel(""), c);
		
		c.gridx = 4;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 14;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 0.2;
		c.weighty = 1;
		add(cartItems, c);
	}
	
	private JPanel createCartItems(){
		JPanel toReturn = new JPanel();
		toReturn.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		ArrayList<Product> userProds = store.getCurrUser().getCart().getProducts();
		ShoppingCart cart = store.getCurrUser().getCart();
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.weighty = 0.1;
		toReturn.add(new JLabel(""), c);
		c.gridwidth = 1;
		
		for(int i = 0; i < userProds.size(); i++){
			Product p = userProds.get(i);
			c.gridy = i + 1;
			JLabel amt = new JLabel(cart.getQuantity(p) + " " + p.getUnit() + "(s)");
			JLabel name = new JLabel(p.getName());
			JLabel itemTotalPrice = new JLabel(String.format("$%9.2f", cart.getQuantity(p) * p.getPrice()));
			c.gridx = 0;
			c.weightx = 0.01;
			c.weighty = 0;
			c.anchor = GridBagConstraints.NORTHEAST;
			c.insets = new Insets(5, 0, 0, 5);
			toReturn.add(amt, c);
			
			c.gridx = 1;
			c.weightx = 0.05;
			c.weighty = 0;
			c.anchor = GridBagConstraints.NORTHWEST;
			c.insets = new Insets(5, 0, 0, 5);
			toReturn.add(name, c);
			
			c.gridx = 2;
			c.weightx = 0;
			c.weighty = 0;
			c.anchor = GridBagConstraints.NORTHEAST;
			c.insets = new Insets(5, 0, 0, 5);
			toReturn.add(itemTotalPrice, c);
		}
		c.gridx = 0;
		c.gridy = userProds.size() + 1;
		c.gridwidth = 3;
		c.weighty = 0.8;
		toReturn.add(new JLabel(""), c);
		
		double total = cart.getTotal();
		JLabel totalLabel = new JLabel("Total");
		JLabel totalPrice = new JLabel(String.format("$%9.2f", total + GroceryStore.TAXRATE * total));
		
		c.gridx = 1;
		c.gridy = userProds.size() + 2;
		c.gridwidth = 1;
		c.weighty = 0;
		c.anchor = GridBagConstraints.EAST;
		toReturn.add(totalLabel, c);
		
		c.gridx = 2;
		c.anchor = GridBagConstraints.EAST;
		toReturn.add(totalPrice, c);
		
		c.gridx = 0;
		c.gridy = userProds.size() + 3;
		c.gridwidth = 3;
		c.weighty = 0.1;
		toReturn.add(new JLabel(""), c);

		return toReturn;
	}
}
