package store.UI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import store.objects.Product;

public class SmallProductPanel extends JPanel {
	private Product contained;
	private JLabel img, name, price, stock, rating;
	private GridBagConstraints imgCons, nameCons, priceCons, stockCons, ratingCons;
	public SmallProductPanel(Product toRep){
		super();
		contained = toRep;
		setPreferredSize(new Dimension(500, 200));
		setLayout(new GridBagLayout());
		img = new JLabel(contained.getImage());
		name = new JLabel(contained.getName());
		String pstring = String.format("%.2lf / %s", contained.getPrice(), contained.getUnit());
		price = new JLabel(pstring);
		String stockString = String.format("%d %s(s)", contained.getQuantity(), contained.getUnit());
		stock = new JLabel(stockString);
		rating = new JLabel(String.format("%.2lf/5.00", contained.averageRating()));
	}
}
