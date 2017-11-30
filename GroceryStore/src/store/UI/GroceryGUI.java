package store.UI;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import store.objects.*;
import store.people.*;
import store.software.*;

import javax.swing.*;

public class GroceryGUI {
	private JFrame frame;
	private GroceryStore store;
	private LoginPanel login;
	
	
	public GroceryGUI(){

		frame = new JFrame("e-Groceries");
		frame.setSize(new Dimension(1280, 720));
		store = new GroceryStore();
		login = new LoginPanel();
		login.addLoginListener(new LoginListener());
		
		frame.add(login);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
