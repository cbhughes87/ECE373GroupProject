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
	private StoreMainPanel test;
	
	
	public GroceryGUI(){

		frame = new JFrame("e-Groceries");
		frame.setMinimumSize(new Dimension(1280, 720));
		store = new GroceryStore();
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
	
	public void showTest(){
		test = new StoreMainPanel(store);
		test.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.pack();
			}
		});
		frame.remove(login);
		frame.add(test);
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
	
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(login.login()){
				showTest();
			}
		}
	}
}
