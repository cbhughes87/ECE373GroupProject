package store.UI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import store.objects.GroceryStore;
import store.objects.Product;
import store.software.Rating;

public class LargeProductPanel extends JPanel {
	private static final int numStars = 5;
	private static final int starImageSize = 30;

	private ArrayList<ActionListener> actionListeners;
	
	private GroceryStore store;
	private Product prod;
	private SmallProductPanel prodPanel;
	private JButton back, add;
	private JSpinner numToAdd;
	private JTabbedPane bottom;
	
	public LargeProductPanel(GroceryStore gstore, Product product){
		this.store = gstore;
		this.prod = product;
		actionListeners = new ArrayList<ActionListener>();
		setLayout(new GridBagLayout());
		prodPanel = new SmallProductPanel(prod, 490, 210);
		back = new JButton("<-Back");
		add = new JButton("Add to Cart");
		SpinnerModel model = new SpinnerNumberModel(1.0, 0, prod.getQuantity(), 0.5);
		numToAdd = new JSpinner(model);
		bottom = new JTabbedPane();
		JPanel info = constructInformation();
		JPanel reviews = constructReviews();
		bottom.addTab("Information", info);
		bottom.addTab("Reviews", reviews);
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for(ActionListener a : actionListeners){
					a.actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "back"));
				}
			}
			
		});
		
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				store.getCurrUser().addToCart(prod, ((SpinnerNumberModel)numToAdd.getModel()).getNumber().intValue());
				for(ActionListener a : actionListeners){
					a.actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "add"));
				}
			}
			
		});
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0;
		c.weighty = 0;
		add(back, c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0;
		c.weighty = 0;
		add(add, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 1;
		c.weighty = 0;
		add(prodPanel, c);
		
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.fill = GridBagConstraints.NONE;
		//c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0;
		c.weighty = 0;
		add(numToAdd, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		//c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 1;
		c.weighty = 1;
		add(bottom, c);
	}
	
	public void addActionListener(ActionListener a){
		actionListeners.add(a);
	}
	
	public boolean removeActionListener(ActionListener a){
		return actionListeners.remove(a);
	}
	
	private JPanel constructInformation(){
		JPanel toReturn = new JPanel();
		JMultilineLabel infoText = new JMultilineLabel(prod.getInfo());
		infoText.setPreferredSize(new Dimension(500, 360));
		infoText.setAlignmentX(LEFT_ALIGNMENT);
		toReturn.add(infoText);
		return toReturn;
	}
	
	private JPanel constructReviews(){
		JPanel toReturn = new JPanel();
		toReturn.setPreferredSize(new Dimension(1280, 360));
		toReturn.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		for(int i = 0; i < prod.getRatings().size(); i++){
			Rating rtg = prod.getRatings().get(i);
			JLabel stars = createStars(rtg);
			JMultilineLabel comment = new JMultilineLabel(rtg.getComment());
			comment.setPreferredSize(new Dimension(600, starImageSize));
			c.gridx = 0;
			c.gridy = i;
			c.weightx = 0;
			c.anchor = GridBagConstraints.NORTHWEST;
			toReturn.add(stars, c);
			c.gridx = 1;
			c.weightx = 1;
			c.anchor = GridBagConstraints.NORTHWEST;
			toReturn.add(comment, c);
		}
		return toReturn;
	}
	
	private JLabel createStars(Rating rating){
		JLabel stars;
		BufferedImage starsImg = new BufferedImage((starImageSize + 1) * 5, starImageSize, BufferedImage.TYPE_INT_ARGB);
		try{
			BufferedImage filled = GroceryGUI.getScaledImage(ImageIO.read(new File("./res/star_full.png")), starImageSize, starImageSize);
			BufferedImage half = GroceryGUI.getScaledImage(ImageIO.read(new File("./res/star_half.png")), starImageSize, starImageSize);
			BufferedImage outline = GroceryGUI.getScaledImage(ImageIO.read(new File("./res/star_outline.png")), starImageSize, starImageSize);
			for(int i = 0; i < numStars; i++){
				double rtg = rating.getScore();
				if(rtg - (double)i >= 1)
					starsImg.getGraphics().drawImage(filled, i * (starImageSize + 1), 0, null);
				else if(rtg - (double)i > 0)
					starsImg.getGraphics().drawImage(half, i * (starImageSize + 1), 0, null);
				else
					starsImg.getGraphics().drawImage(outline, i * (starImageSize + 1), 0, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		stars = new JLabel(new ImageIcon(starsImg));
		stars.setAlignmentX(LEFT_ALIGNMENT);
		stars.setAlignmentY(CENTER_ALIGNMENT);
		return stars;
	}
}
