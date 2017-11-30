package store.UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import store.objects.GroceryStore;

public class LoginPanel extends JPanel {
	private JTextField user;
	private JPasswordField pass;
	private JLabel userLabel, passLabel;
	private JButton loginButton;
	public LoginPanel(){
		super();
		int loginTopBottom = 250;
		int loginTextSize = 20;
		Font loginFont = new Font(null, Font.PLAIN, loginTextSize);
		user = new JTextField(10);
		pass = new JPasswordField(10);
		userLabel = new JLabel("Username");
		passLabel = new JLabel("Password");
		loginButton = new JButton("Log In");
		
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
		add(Box.createRigidArea(new Dimension(100, loginTopBottom)));
	}
}
