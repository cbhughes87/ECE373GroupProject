package store.UI;
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
	private GroceryStore store;
	private LoginPanel login;
	private StoreMainPanel mainPanel;
	private CartPanel cartView;
	private CheckoutPanel checkout;
	private LargeProductPanel productView;
	private AdminProductPanel adminProducts;
	private AdminUserPanel adminUsers;
	
	
	public GroceryGUI(GroceryStore store){

		frame = new JFrame("e-Groceries");
		frame.setMinimumSize(new Dimension(1280, 720));
		this.store = store;
		login = new LoginPanel(store);
		login.addLoginListener(new LoginListener());
		
		frame.add(login);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public GroceryStore getStore(){
		return store;
	}
	
	public void showMainPanel(){
		refreshMainPanel();
		frame.remove(login);
		frame.add(mainPanel);
		frame.pack();
	}
	
	public void refreshMainPanel(){
		mainPanel = new StoreMainPanel(store);
		mainPanel.addActionListener(new MainPanelListener());
	}
	
	public static BufferedImage getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(login.login()){
				showMainPanel();
			}
		}
	}
	
	private class CartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame.remove(cartView);	
			if(e.getActionCommand().equals("back")){
				frame.add(mainPanel);
			} else if(e.getActionCommand().equals("checkout")){
				checkout = new CheckoutPanel(store);
				checkout.addActionListener(new CheckoutListener());
				frame.add(checkout);
			}
			
			frame.revalidate();
			frame.repaint();
			frame.pack();
		}
	}
	
	private class CheckoutListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("back")){
				frame.remove(checkout);	
				frame.add(mainPanel);
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
				frame.remove(productView);	
				frame.add(mainPanel);
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
				frame.remove(mainPanel);
				cartView = new CartPanel(store);
				cartView.addActionListener(new CartListener());
				frame.add(cartView);
				frame.pack();
			}
			else if(e.getActionCommand().equals("product")){
				frame.remove(mainPanel);
				SmallProductPanel src = (SmallProductPanel)e.getSource();
				productView = new LargeProductPanel(store, src.getContained());
				productView.addActionListener(new LargeProductListener());
				frame.add(productView);
				frame.revalidate();
				frame.repaint();
				frame.pack();
			}
		}
	}
}
