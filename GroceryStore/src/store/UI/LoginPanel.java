package store.UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import store.objects.GroceryStore;
import store.people.User;
import store.people.UserType;

public class LoginPanel extends JPanel {
	private JTextField user;
	private JPasswordField pass;
	private JLabel userLabel, passLabel;
	private JButton loginButton;
	private JButton newUserButton;
	private JLabel errLabel;
	
	private GroceryStore store;
	public LoginPanel(GroceryStore store){
		super();
		this.store = store;
		int loginTopBottom = 250;
		int loginTextSize = 20;
		Font loginFont = new Font(null, Font.PLAIN, loginTextSize);
		user = new JTextField(10);
		pass = new JPasswordField(10);
		userLabel = new JLabel("Username");
		passLabel = new JLabel("Password");
		loginButton = new JButton("Log In");
		newUserButton = new JButton("New User");
		errLabel = new JLabel("");
		
		user.setMaximumSize(new Dimension(120, 20));
		user.setAlignmentX(Component.CENTER_ALIGNMENT);
		user.setFont(loginFont);
		
		pass.setMaximumSize(new Dimension(120, 20));
		pass.setAlignmentX(Component.CENTER_ALIGNMENT);
		pass.setEchoChar('*');
		pass.setFont(loginFont);
		
		userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		userLabel.setFont(loginFont);
		
		passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		passLabel.setFont(loginFont);
		
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.setFont(loginFont);
		
		newUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newUserButton.setFont(loginFont);
		newUserButton.addActionListener(new NewUserListener());
		
		errLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		errLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errLabel.setFont(loginFont);
		
		
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(500, 500));
		add(Box.createRigidArea(new Dimension(100, loginTopBottom)));
		add(userLabel);
		add(user);
		add(Box.createVerticalGlue());
		add(passLabel);
		add(pass);
		add(Box.createVerticalGlue());
		add(loginButton);
		add(newUserButton);
		add(errLabel);
		add(Box.createRigidArea(new Dimension(100, loginTopBottom)));
		
	}
	public boolean login(){
		User login = store.getUser(user.getText(), new String(pass.getPassword()));
		if(login != null){
			store.setCurrUser(login);
			return true;
		}
		errLabel.setText("<html><font color='red'>Incorrect username or password.</font color></html>");
		return false;
	}
	public void addLoginListener(ActionListener a){
		loginButton.addActionListener(a);
	}
	private class NewUserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String err = store.addUser(user.getText(), new String(pass.getPassword()), UserType.SHOPPER);
			if(!err.equals("")){
				errLabel.setText("<html><font color='red'>" + err + "</font color></html>");
			}else{
				errLabel.setText("<html><font color='green'>User created successfully.</font color></html>");
			}
			
		}
	}
}
