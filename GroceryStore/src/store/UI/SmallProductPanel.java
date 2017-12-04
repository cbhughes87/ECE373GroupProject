package store.UI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import store.objects.Product;

public class SmallProductPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3408113148015332149L;
	private int starImageSize;
	private static final int numStars = 5;
	
	private Product contained;
	private JLabel img, name, price, stock, rating, stars;
	private int width, height;
	public SmallProductPanel(Product toRep, int width, int height){
		super();
		this.width = width;
		this.height = height;
		starImageSize = height * 4 / 15;
		contained = toRep;
		setMinimumSize(new Dimension(this.width, this.height));
		setLayout(new GridBagLayout());
		createLabels();
		createStars();
		layoutLabels();
	}
	
	public Product getContained(){
		return contained;
	}
	
	private void layoutLabels(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 3;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 0, 0);
		add(img, c);
		
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0, height / 15, 0, 0);
		add(name, c);
		
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.5;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(height / 15, height / 15, height / 15, 0);
		add(price, c);
		
		c.gridx = 5;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.5;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(height / 15, 0, height / 15, height / 15);
		add(stock, c);
		
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.weightx = 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(height / 15, height / 15, 0, 0);
		add(stars, c);
		
		c.gridx = 6;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(height / 15, height / 15, 0, height / 15);
		add(rating, c);
	}
	
	private void createStars(){
		BufferedImage starsImg = new BufferedImage((starImageSize + 1) * 5, starImageSize, BufferedImage.TYPE_INT_ARGB);
		try{
			BufferedImage filled = GroceryGUI.getScaledImage(ImageIO.read(new File("./res/star_full.png")), starImageSize, starImageSize);
			BufferedImage half = GroceryGUI.getScaledImage(ImageIO.read(new File("./res/star_half.png")), starImageSize, starImageSize);
			BufferedImage outline = GroceryGUI.getScaledImage(ImageIO.read(new File("./res/star_outline.png")), starImageSize, starImageSize);
			for(int i = 0; i < numStars; i++){
				double rtg = contained.averageRating();
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
	}
	
	private void createLabels(){
		img = new JLabel(new ImageIcon(GroceryGUI.getScaledImage(contained.getImage(), height, height)));
		img.setAlignmentX(CENTER_ALIGNMENT);
		img.setAlignmentY(CENTER_ALIGNMENT);
		name = new JLabel(contained.getName());
		name.setAlignmentX(CENTER_ALIGNMENT);
		name.setAlignmentY(CENTER_ALIGNMENT);
		String pstring = String.format("$%.2f / %s", contained.getPrice(), contained.getUnit());
		price = new JLabel(pstring);
		price.setAlignmentX(LEFT_ALIGNMENT);
		price.setAlignmentY(CENTER_ALIGNMENT);
		String stockString = String.format("%.2f %s(s)", contained.getQuantity(), contained.getUnit());
		stock = new JLabel(stockString);
		stock.setAlignmentX(RIGHT_ALIGNMENT);
		stock.setAlignmentY(CENTER_ALIGNMENT);
		rating = new JLabel(String.format("%.2f/5.00", contained.averageRating()));
		rating.setAlignmentX(RIGHT_ALIGNMENT);
		rating.setAlignmentY(CENTER_ALIGNMENT);
	}
}
