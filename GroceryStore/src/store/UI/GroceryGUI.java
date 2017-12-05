package store.UI;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import store.objects.*;
import store.people.*;
import store.software.*;

import javax.swing.*;

public class GroceryGUI {
	private JFrame frame;
	private JPanel currPanel;
	private GroceryStore store;
	private LoginPanel login;
	private StoreMainPanel mainPanel;
	private NewUserPanel newUserPanel;
	private CartPanel cartView;
	private CheckoutPanel checkout;
	private LargeProductPanel productView;
	private AdminProductPanel adminProducts;
	private StockPanel stockPanel;
	private AdminUserPanel adminUsers;
	private ProductInfoPanel edit;
	private JMenuBar menu;
	private JMenu admin;
	private JMenu file;
	private JMenu employee;
	private JMenuItem menuAdminProducts;
	private JMenuItem logout;
	private JMenuItem menuManageInventory;
	
	
	public GroceryGUI(GroceryStore store){
		currPanel = null;
		frame = new JFrame("e-Groceries");
		frame.setMinimumSize(new Dimension(1280, 720));
		frame.setLayout(new BorderLayout());
		this.store = store;		
		showLoginPanel();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu = new JMenuBar();
		
		admin = new JMenu("Administration");
		menuAdminProducts = new JMenuItem("Products");
		menuAdminProducts.addActionListener(new MenuListener());
		admin.add(menuAdminProducts);
		
		file = new JMenu("File");
		logout = new JMenuItem("Log Out");
		logout.addActionListener(new MenuListener());
		file.add(logout);
		
		employee = new JMenu("Employees");
		menuManageInventory = new JMenuItem("Manage Stock");
		menuManageInventory.addActionListener(new MenuListener());
		employee.add(menuManageInventory);
		
		menu.add(file);
		menu.add(admin);
		menu.add(employee);
		
	}
	
	public void showLoginPanel() {
		login = new LoginPanel(store);
		login.addActionListener(new LoginListener());
		if(currPanel != null){
			frame.remove(currPanel);
		}
		currPanel = login;
		frame.add(login, BorderLayout.CENTER);
		frame.pack();
	}
	
	public GroceryStore getStore(){
		return store;
	}
	
	public void showMainPanel(){
		refreshMainPanel();
		frame.remove(currPanel);
		currPanel = mainPanel;
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}
	
	public void refreshMainPanel(){
		mainPanel = new StoreMainPanel(store);
		mainPanel.addActionListener(new MainPanelListener());
		
	}

	public void showNewUserPanel() {
		newUserPanel = new NewUserPanel(store);
		newUserPanel.addNewUserListener(new NewUserListener());
		frame.remove(currPanel);
		currPanel = newUserPanel;
		frame.add(newUserPanel, BorderLayout.CENTER);
		frame.pack();
	}
	
	public static BufferedImage getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	private void doLogout(){
		frame.remove(menu);
		store.setCurrUser(null);
		showLoginPanel();
	}
	
	private class MenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JMenuItem src = (JMenuItem)e.getSource();
			if(src.equals(logout)){
				doLogout();
			} 
			else if(src.equals(menuAdminProducts)){
				adminProducts = new AdminProductPanel(store);
				adminProducts.addActionListener(new AdminProductListener());
				frame.remove(currPanel);
				frame.add(adminProducts, BorderLayout.CENTER);
				currPanel = adminProducts;
				frame.revalidate();
				frame.repaint();
				frame.pack();
			}
			else if(src.equals(menuManageInventory)) {
				stockPanel = new StockPanel(store);
				stockPanel.addActionListener(new StockPanelListener());
				frame.remove(currPanel);
				frame.add(stockPanel);
				currPanel = stockPanel;
				frame.revalidate();
				frame.repaint();
				frame.pack();
			}
		}
	}
	
	
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Login")) {
				if(login.login()){
					frame.add(menu, BorderLayout.PAGE_START);
					showMainPanel();
				}
			}
			if(e.getActionCommand().equals("New User")) {
				showNewUserPanel();
			}
			
		}
	}
	
	private class CartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame.remove(currPanel);	
			if(e.getActionCommand().equals("back")){
				frame.add(mainPanel, BorderLayout.CENTER);
				currPanel = mainPanel;
			} else if(e.getActionCommand().equals("checkout")){
				checkout = new CheckoutPanel(store);
				checkout.addActionListener(new CheckoutListener());
				frame.add(checkout, BorderLayout.CENTER);
				currPanel = checkout;
			}
			
			frame.revalidate();
			frame.repaint();
			frame.pack();
		}
	}
	///////WORKING ON THIS
	private class NewUserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(newUserPanel.newUser()) {
				showLoginPanel();
				//addCancel Button
			}
			
		}
		
	}
	
	private class CheckoutListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("back")){
				frame.remove(currPanel);	
				frame.add(mainPanel, BorderLayout.CENTER);
				currPanel = mainPanel;
				frame.revalidate();
				frame.repaint();
				frame.pack();
			}
			else if(e.getActionCommand().equals("order")){
				refreshMainPanel();
			}
		}
	}
	
	private class LargeProductListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("back")){
				frame.remove(currPanel);	
				frame.add(mainPanel, BorderLayout.CENTER);
				currPanel = mainPanel;
				frame.revalidate();
				frame.repaint();
				frame.pack();
			} else if(e.getActionCommand().equals("add")){
				refreshMainPanel();
			}
		}
	}
	
	private class MainPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("refresh"))
				frame.pack();
			else if(e.getActionCommand().equals("viewcart")){
				frame.remove(currPanel);
				cartView = new CartPanel(store);
				cartView.addActionListener(new CartListener());
				frame.add(cartView, BorderLayout.CENTER);
				currPanel = cartView;
				frame.pack();
			}
			else if(e.getActionCommand().equals("product")){
				frame.remove(currPanel);
				SmallProductPanel src = (SmallProductPanel)e.getSource();
				productView = new LargeProductPanel(store, src.getContained());
				productView.addActionListener(new LargeProductListener());
				frame.add(productView, BorderLayout.CENTER);
				currPanel = productView;
				frame.revalidate();
				frame.repaint();
				frame.pack();
			}
		}
	}
	private class StockPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Login")) {
				if(login.login()){
					frame.add(menu, BorderLayout.PAGE_START);
					showMainPanel();
				}
			}
			if(e.getActionCommand().equals("back")) {
				frame.remove(currPanel);
				frame.add(mainPanel, BorderLayout.CENTER);
				currPanel = mainPanel;
			}
			
		}
	}
	private class AdminProductListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("back")){
				frame.remove(currPanel);
				frame.add(mainPanel, BorderLayout.CENTER);
				currPanel = mainPanel;
			}
			else if(e.getActionCommand().equals("remove")){
				refreshMainPanel();
			}
			else {
				frame.remove(currPanel);
				Product change = (Product)e.getSource();
				if(change.getIDNum() == -1)
					change = null;
				edit = new ProductInfoPanel(store, change);
				edit.addActionListener(new LargeProductListener());
				currPanel = edit;
				frame.add(edit, BorderLayout.CENTER);
			}
			frame.revalidate();
			frame.repaint();
			frame.pack();
		}
	}
}
