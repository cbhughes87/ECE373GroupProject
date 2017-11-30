package store.UI;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

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

		
		frame.add(login);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
