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
import store.people.Shopper;
import store.people.User;

public class AdminUserPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3373655721223889533L;

	private static final int topBottomSpacing = 200;
	
	private GroceryStore store;
	private JLabel productMan;
	private JButton create, remove, edit, back;
	private User dummy;
	
	private ArrayList<ActionListener> actionListeners;
	
	public AdminUserPanel(GroceryStore gstore){
		store = gstore;
		dummy = new Shopper();
		dummy.setName(null);
		actionListeners = new ArrayList<ActionListener>();
		productMan = new JLabel("User Management");
		create = new JButton("Create User");
		remove = new JButton("Remove User");
		edit = new JButton("Edit User");
		back = new JButton("<- Back");
		
		productMan.setAlignmentX(CENTER_ALIGNMENT);
		create.setEnabled(store.getCurrUser().getPerms().getPermission("add"));
		create.setAlignmentX(CENTER_ALIGNMENT);
		create.addActionListener(new UserChangeListener());
		
		remove.setEnabled(store.getCurrUser().getPerms().getPermission("rem"));
		remove.setAlignmentX(CENTER_ALIGNMENT);
		remove.addActionListener(new UserChangeListener());
		
		edit.setEnabled(store.getCurrUser().getPerms().getPermission("perms"));
		edit.setAlignmentX(CENTER_ALIGNMENT);
		edit.addActionListener(new UserChangeListener());
		
		back.setAlignmentX(CENTER_ALIGNMENT);
		back.addActionListener(new UserChangeListener());
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(topBottomSpacing));
		add(productMan);
		add(Box.createGlue());
		add(create);
		add(remove);
		add(edit);
		add(back);
		add(Box.createGlue());
		add(Box.createVerticalStrut(topBottomSpacing));
	}
	
	public void addActionListener(ActionListener a){
		actionListeners.add(a);
	}
	
	public boolean removeActionListener(ActionListener a){
		return actionListeners.remove(a);
	}
	
	private class UserChangeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if(source.equals(create)){
				createUser();
			}
			else if(source.equals(remove)){
				removeUser();
			}
			else if(source.equals(edit)){
				editUser();
			}
			else if(source.equals(back)){
				for(ActionListener a : actionListeners){
					a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "back"));
				}
			}
		}
		
		private void editUser() {
			User toEdit = getUser("Edit");
			if(toEdit == null)
				return;
			for(ActionListener a : actionListeners){
				a.actionPerformed(new ActionEvent(toEdit, ActionEvent.ACTION_PERFORMED, "edituser"));
			}
		}
		
		private void removeUser() {
			User toEdit = getUser("Remove");
			if(toEdit == null)
				return;
			store.getUsers().remove(toEdit);
			for(ActionListener a : actionListeners){
				a.actionPerformed(new ActionEvent(toEdit, ActionEvent.ACTION_PERFORMED, "remove"));
			}
		}

		private void createUser() {
			for(ActionListener a : actionListeners){
				a.actionPerformed(new ActionEvent(dummy, ActionEvent.ACTION_PERFORMED, "createuser"));
			}
		}
		
		private User getUser(String custom){
			ArrayList<User> users = store.getUsers();
			Object[] strs = new Object[users.size()];
			for(int i = 0; i < users.size(); i++){
				strs[i] = users.get(i).getName();
			}
			String choice = (String)JOptionPane.showInputDialog(getParent(), 
					"Choose a User to " + custom.toLowerCase(), 
					custom + " User", 
					JOptionPane.PLAIN_MESSAGE, null, strs, strs[0]);
			User toEdit = null;
			for(User u : users){
				if(u.getName().equals(choice)){
					toEdit = u;
					break;
				}
			}
			return toEdit;
		}
	}
}
