package store.UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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

public class LoginPanel extends JPanel {
	private JTextField user;
	private JPasswordField pass;
	private JLabel userLabel, passLabel;
	private JButton loginButton;
	private JButton newUserButton;
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
		newUserButton = new JButton("New User");
		
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
		add(Box.createRigidArea(new Dimension(100, loginTopBottom)));
		
	}
	public String[] getText(){
		return new String[]{user.getText(), pass.getPassword().toString()};
	}
	public void addLoginListener(ActionListener a){
		loginButton.addActionListener(a);
	}
	public void addNewUserListener(ActionListener a){
		newUserButton.addActionListener(a);
	}
}
