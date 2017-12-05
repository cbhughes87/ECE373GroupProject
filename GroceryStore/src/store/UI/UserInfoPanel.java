package store.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import store.objects.GroceryStore;
import store.people.Shopper;
import store.people.User;
import store.software.Permissions;

public class UserInfoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8214954303491656175L;

	private static final int leftRightSpacing = 200;
	
	private GroceryStore store;
	private User toEdit = null;
	private JLabel title, name, password, permissions;
	private JTextField nameField;
	private JPasswordField passField;
	private JCheckBox shop, stock, invent, log, add, rem, edit;
	private JButton save, cancel;
	
	private ArrayList<ActionListener> actionListeners;
	
	public UserInfoPanel(GroceryStore gstore, User u){
		store = gstore;
		actionListeners = new ArrayList<ActionListener>();
		toEdit = u;
		title = new JLabel("Set User Information");
		title.setHorizontalAlignment(JLabel.CENTER);
		
		name = new JLabel("Username");
		password = new JLabel("Password");
		permissions = new JLabel("Permissions");
		
		nameField = new JTextField(15);
		passField = new JPasswordField(15);
		passField.setEchoChar('*');
		
		shop = new JCheckBox("Shop");
		stock = new JCheckBox("Stock");
		invent = new JCheckBox("Inventory");
		log = new JCheckBox("Logs");
		add = new JCheckBox("Add Users");
		rem = new JCheckBox("Remove Users");
		edit = new JCheckBox("Edit Users");
		
		if(toEdit != null){
			nameField.setText(toEdit.getName());
			shop.setSelected(toEdit.getPerms().getPermission("shop"));
			stock.setSelected(toEdit.getPerms().getPermission("stock"));
			invent.setSelected(toEdit.getPerms().getPermission("inventory"));
			log.setSelected(toEdit.getPerms().getPermission("logs"));
			add.setSelected(toEdit.getPerms().getPermission("add"));
			rem.setSelected(toEdit.getPerms().getPermission("rem"));
			edit.setSelected(toEdit.getPerms().getPermission("perms"));
		}
		
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		
		save.addActionListener(new ButtonListener());
		cancel.addActionListener(new ButtonListener());
				
		setLayout(new GridBagLayout());
		
		layoutComponents();
	}
	
	private void layoutComponents(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		add(Box.createHorizontalStrut(leftRightSpacing), c);
		c.gridx = 4;
		add(Box.createHorizontalStrut(leftRightSpacing), c);
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 3;
		c.gridx = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(title, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		add(name, c);
		
		c.gridx = 1;
		c.gridy = 2;
		add(nameField, c);
		
		c.gridx = 1;
		c.gridy = 4;
		add(password, c);
		
		c.gridx = 1;
		c.gridy = 5;
		add(passField, c);
		
		c.gridx = 1;
		c.gridy = 6;
		add(save, c);
		
		c.gridx = 2;
		c.gridy = 1;
		add(permissions, c);
		
		c.gridx = 2;
		c.gridy = 2;
		add(shop, c);
		
		c.gridx = 2;
		c.gridy = 3;
		add(stock, c);
		
		c.gridx = 2;
		c.gridy = 4;
		add(invent, c);
		
		c.gridx = 2;
		c.gridy = 5;
		add(log, c);
		
		c.gridx = 3;
		c.gridy = 2;
		add(add, c);
		
		c.gridx = 3;
		c.gridy = 3;
		add(rem, c);
		
		c.gridx = 3;
		c.gridy = 4;
		add(edit, c);
		
		c.gridx = 3;
		c.gridy = 6;
		add(cancel, c);
	}
	
	public void addActionListener(ActionListener a){
		actionListeners.add(a);
	}
	
	public boolean removeActionListener(ActionListener a){
		return actionListeners.remove(a);
	}
	
	private void save(){
		User u;
		if(toEdit == null){
			u = new Shopper();
			store.getUsers().add(u);
		}
		else
			u = toEdit;
		u.setName(nameField.getText());
		u.setPassword(new String(passField.getPassword()));
		Permissions p = new Permissions(shop.isSelected(), 
				stock.isSelected(), 
				invent.isSelected(), 
				add.isSelected(), 
				rem.isSelected(), 
				edit.isSelected(), 
				log.isSelected());
		u.setPerms(p);
	}
	
	private void exit(){
		for(ActionListener a : actionListeners){
			a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "back"));
		}
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if(source.equals(save)){
				save();
				exit();
			}
			else if(source.equals(cancel)){
				exit();
			}
		}
	}
}
