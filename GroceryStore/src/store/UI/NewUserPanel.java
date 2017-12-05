package store.UI;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import store.objects.GroceryStore;
import store.people.User;
import store.people.UserType;

public class NewUserPanel extends JPanel {
	private JTextField user;
	private JPasswordField pass;
	private JLabel userLabel, passLabel;
	private JButton createUserButton;
	private JLabel errLabel;
	private ButtonGroup userGroup;
	private JRadioButton shopper;
	private JRadioButton employee;
	private JRadioButton admin;
	private GroceryStore store;
	public NewUserPanel(GroceryStore store){
		super();
		this.store = store;
		int loginTopBottom = 250;
		int loginTextSize = 20;
		Font loginFont = new Font(null, Font.PLAIN, loginTextSize);
		
		setBackground(Color.white);
		
		user = new JTextField(10);
		pass = new JPasswordField(10);
		userLabel = new JLabel("Username");
		passLabel = new JLabel("Password");
		createUserButton = new JButton("Create User");
		errLabel = new JLabel("");
		
		userGroup = new ButtonGroup();
		shopper = new JRadioButton("Shopper");
		shopper.setBackground(Color.white);
		employee = new JRadioButton("Employee");
		employee.setBackground(Color.white);
		admin = new JRadioButton("Admin");
		admin.setBackground(Color.white);
		
//		shopper.setHorizontalAlignment(JRadioButton.LEADING);
//		shopper.setHorizontalTextPosition(JRadioButton.LEADING);
//		shopper.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//
//		employee.setHorizontalAlignment(JRadioButton.LEADING);
//		employee.setHorizontalTextPosition(JRadioButton.LEADING);
//		employee.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//		
//		admin.setHorizontalAlignment(JRadioButton.LEADING);
//		admin.setHorizontalTextPosition(JRadioButton.LEADING);
//		admin.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		shopper.setAlignmentX(Component.CENTER_ALIGNMENT);
		shopper.setFont(loginFont);
		employee.setAlignmentX(Component.CENTER_ALIGNMENT);
		employee.setFont(loginFont);
		admin.setAlignmentX(Component.CENTER_ALIGNMENT);
		admin.setFont(loginFont);
		
		userGroup.add(shopper);
		userGroup.add(employee);
		userGroup.add(admin);
		
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
		
		createUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createUserButton.setFont(loginFont);
		createUserButton.addActionListener(new NewUserListener());
		
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
		add(shopper);
		add(employee);
		add(admin);
		add(Box.createVerticalGlue());
		add(createUserButton);
		add(errLabel);
		add(Box.createRigidArea(new Dimension(100, loginTopBottom)));
		
	}
	public boolean newUser(){
		store.addUser(user.getText(), new String(pass.getPassword()), getUserType());
		//requires checking of password length
//		errLabel.setText("<html><font color='red'>Incorrect username or password.</font color></html>");
		return true;
	}
	
	public UserType getUserType() {
		if (shopper.isSelected()) {
			return UserType.SHOPPER;
		}
		else if (employee.isSelected()) {
			return UserType.EMPLOYEE;
		}
		else if(admin.isSelected()) {
			return UserType.ADMIN;
		}
		else {
			return UserType.SHOPPER;
		}
	}
	
	public void addNewUserListener(ActionListener a){
		createUserButton.addActionListener(a);
	}
	private class NewUserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			//			String err = store.addUser(user.getText(), new String(pass.getPassword()), UserType.SHOPPER);
//			if(!err.equals("")){
//				errLabel.setText("<html><font color='red'>" + err + "</font color></html>");
//			}else{
//				errLabel.setText("<html><font color='green'>User created successfully.</font color></html>");
//			}
			
		}
	}
}
