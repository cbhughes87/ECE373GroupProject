package store.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import store.objects.GroceryStore;
import store.objects.Product;

public class CartPanel extends JPanel {
	private JButton back, checkout;
	private ArrayList<JLabel[]> productLabels;
	private JLabel subtotal, tax, total, title;
	
	private GroceryStore store;
	private ArrayList<ActionListener> actionListeners;
	
	public CartPanel(GroceryStore store){
		super();
		this.store = store;
		back = new JButton("<-Back");
		title = new JLabel("Shopping Cart");
		checkout = new JButton("Proceed to Checkout");
		productLabels = new ArrayList<JLabel[]>();
		subtotal = new JLabel("Subtotal:");
		tax = new JLabel("Tax:");
		total = new JLabel("Total:");
		actionListeners = new ArrayList<ActionListener>();
		constructProductLabels();
		setLayout(new GridBagLayout());
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for(ActionListener a : actionListeners){
					a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "back"));
				}
			}
		});
		checkout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for(ActionListener a : actionListeners){
					a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "checkout"));
				}
			}
		});
		drawGraphics();
	}
	
	private void drawGraphics(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0;
		c.weighty = 0.1;
		c.anchor = GridBagConstraints.NORTHWEST;
		add(back, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0.1;
		c.anchor = GridBagConstraints.NORTH;
		add(title, c);
		
		c.gridx = 5;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0;
		c.weighty = 0.1;
		c.anchor = GridBagConstraints.NORTHEAST;
		add(checkout, c);
		
		for(int i = 0; i < productLabels.size(); i++){
			for(int j = 0; j < productLabels.get(i).length; j++){
				c.gridx = j + 1;
				c.gridy = i + 2;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.weightx = 0.3;
				c.weighty = 0;
				switch(j){
				case 0:
					c.anchor = GridBagConstraints.WEST;
					break;
				case 3:
					c.anchor = GridBagConstraints.EAST;
					break;
				default:
					c.anchor = GridBagConstraints.CENTER;
					
				}
				add(productLabels.get(i)[j], c);
			}
		}
		
		c.gridx = 0;
		c.gridy = productLabels.size() + 2;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0.5;
		c.anchor = GridBagConstraints.CENTER;
		add(new JLabel(""), c);
		
		c.gridx = 3;
		c.gridy = productLabels.size() + 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0.01;
		c.anchor = GridBagConstraints.LINE_END;
		add(subtotal, c);
		
		JLabel subcost = new JLabel();
		double subtotalCost = 0;
		for(Product p : store.getCurrUser().getCart().getProducts()){
			subtotalCost += store.getCurrUser().getCart().getQuantity(p) * p.getPrice();
		}
		subcost.setText(String.format("$%9.2f", subtotalCost));
		c.gridx = 4;
		c.gridy = productLabels.size() + 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0.01;
		c.anchor = GridBagConstraints.LINE_END;
		add(subcost, c);
		
		c.gridx = 3;
		c.gridy = productLabels.size() + 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0.01;
		c.anchor = GridBagConstraints.LINE_END;
		add(tax, c);
		
		JLabel taxcost = new JLabel();
		double tax = GroceryStore.TAXRATE * subtotalCost;
		taxcost.setText(String.format("$%9.2f", tax));
		c.gridx = 4;
		c.gridy = productLabels.size() + 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0.01;
		c.anchor = GridBagConstraints.LINE_END;
		add(taxcost, c);
		
		c.gridx = 3;
		c.gridy = productLabels.size() + 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0.01;
		c.anchor = GridBagConstraints.LINE_END;
		add(total, c);
		
		JLabel totalcost = new JLabel(String.format("$%9.2f", subtotalCost + tax));
		c.gridx = 4;
		c.gridy = productLabels.size() + 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0.01;
		c.anchor = GridBagConstraints.LINE_END;
		add(totalcost, c);
		
		c.gridx = 0;
		c.gridy = productLabels.size() + 6;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0.1;
		c.anchor = GridBagConstraints.CENTER;
		add(new JLabel(""), c);
	}
	
	private void constructProductLabels(){
		JLabel[] header = new JLabel[]{
			new JLabel("Name"),
			new JLabel("Quantity"),
			new JLabel("Unit Price"),
			new JLabel("Total Price")
		};
		productLabels.add(header);
		for(Product prod : store.getCurrUser().getCart().getProducts()){
			JLabel[] newArr = new JLabel[]{
				new JLabel(prod.getName()),
				new JLabel(String.format("%.2f %s", store.getCurrUser().getCart().getQuantity(prod), prod.getUnit())),
				new JLabel(String.format("$%9.2f / %s", prod.getPrice(), prod.getUnit())),
				new JLabel(String.format("$%9.2f", prod.getPrice() * store.getCurrUser().getCart().getQuantity(prod)))
			};
			productLabels.add(newArr);
		}
	}
	
	public void addActionListener(ActionListener a){
		actionListeners.add(a);
	}
	public void removeActionListener(ActionListener a){
		actionListeners.remove(a);
	}
	public ArrayList<ActionListener> getActionListeners(){
		return actionListeners;
	}
}
