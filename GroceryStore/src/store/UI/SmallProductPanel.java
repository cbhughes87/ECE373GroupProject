package store.UI;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import store.objects.Product;

public class SmallProductPanel extends JPanel {
	private Product contained;
	private JLabel imgLabel, name, price, stock, rating;
	public SmallProductPanel(Product toRep){
		super();
		contained = toRep;
		setPreferredSize(new Dimension(500, 200));
		
	}
}
