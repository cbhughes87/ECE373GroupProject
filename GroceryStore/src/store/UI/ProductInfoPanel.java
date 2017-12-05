package store.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import store.objects.Department;
import store.objects.GroceryStore;
import store.objects.Product;

public class ProductInfoPanel extends JPanel {
	private static final int leftRightSpacing = 100;
	private GroceryStore store;
	private Product product = null;
	private JLabel title, name, department, info, quantity, unit, tags, price, img;
	private JTextField nameField, unitField, imgField, deptField;
	private JFormattedTextField quantityField, priceField;
	private JTextArea infoArea, tagsArea;
	private JButton save, cancel;
	private DecimalFormat fmt;
	
	private ArrayList<ActionListener> actionListeners;
	
	public ProductInfoPanel(GroceryStore gstore, Product prod){
		store = gstore;
		product = prod;
		
		actionListeners = new ArrayList<ActionListener>();
		
		title = new JLabel("Set Product Information");
		department = new JLabel("Department");
		name = new JLabel("Name");
		info = new JLabel("Information");
		quantity = new JLabel("Quantity");
		unit = new JLabel("Units");
		tags = new JLabel("Tags");
		price = new JLabel("Price");
		img = new JLabel("Image");
		
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		
		save.addActionListener(new ButtonListener());
		cancel.addActionListener(new ButtonListener());
		
		nameField = new JTextField(25);
		unitField = new JTextField(5);
		imgField = new JTextField(35);
		deptField = new JTextField(25);
		
		fmt = new DecimalFormat("####.##");
		quantityField = new JFormattedTextField(fmt);
		quantityField.setColumns(9);
		priceField = new JFormattedTextField(fmt);
		priceField.setColumns(9);
		
		infoArea = new JTextArea(10, 40);
		infoArea.setWrapStyleWord(true);
		infoArea.setLineWrap(true);
		tagsArea = new JTextArea(5, 40);
		tagsArea.setWrapStyleWord(true);
		tagsArea.setLineWrap(true);
		
		if(product != null){
			nameField.setText(product.getName());
			deptField.setText(product.getDepartment().getName());
			unitField.setText(product.getUnit());
			imgField.setText(product.getImagePath());
			quantityField.setValue(product.getQuantity());
			priceField.setValue(product.getPrice());
			infoArea.setText(product.getInfo());
			StringBuilder tagsString = new StringBuilder();
			for(String tag : product.getTags()){
				tagsString.append(tag + "; ");
			}
			tagsArea.setText(tagsString.toString());
		}
		
		
		
		setLayout(new GridBagLayout());
		
		arrangeComponents();
		
	}
	
	public void addActionListener(ActionListener a){
		actionListeners.add(a);
	}
	
	public boolean removeActionListener(ActionListener a){
		return actionListeners.remove(a);
	}
	
	private void save() throws IOException {
		Product prod;
		if(product != null)
			prod = product;
		else{
			prod = new Product();
			boolean deptFound = false;
			for(Department d : store.getDepartments()){
				if(d.getName().equals(deptField.getText())){
					prod.setDepartment(d);
					deptFound = true;
					break;
				}
			}
			if(!deptFound){
				Department toAdd = new Department();
				toAdd.setName(deptField.getText());
				store.addDepartment(toAdd);
				prod.setDepartment(toAdd);
			}
		}
		prod.setName(nameField.getText());
		prod.setInfo(infoArea.getText());
		
		
		prod.setPrice(((Number)priceField.getValue()).doubleValue());
		prod.setQuantity(((Number)quantityField.getValue()).intValue());
		prod.setUnit(unitField.getText());
		Scanner sc = new Scanner(tagsArea.getText());
		sc.useDelimiter(";");
		while(sc.hasNext()){
			prod.addTag(sc.next().trim());
		}
		sc.close();
		prod.openImage(imgField.getText());
		for(ActionListener a : actionListeners){
			a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add"));
		}
	}
	
	private void exit(){
		for(ActionListener a : actionListeners){
			a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "back"));
		}
	}
	
	private void arrangeComponents(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		add(Box.createHorizontalStrut(leftRightSpacing), c);
		c.gridx = 3;
		add(Box.createHorizontalStrut(leftRightSpacing), c);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		add(title, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		add(name, c);
		
		c.gridx = 1;
		c.gridy = 2;
		add(nameField, c);
		
		c.gridx = 1;
		c.gridy = 3;
		add(quantity, c);
		
		c.gridx = 1;
		c.gridy = 4;
		add(quantityField, c);
		
		c.gridx = 1;
		c.gridy = 5;
		add(unit, c);
		
		c.gridx = 1;
		c.gridy = 6;
		add(unitField, c);
		
		c.gridx = 1;
		c.gridy = 7;
		add(price, c);
		
		c.gridx = 1;
		c.gridy = 8;
		add(priceField, c);
		
		c.gridx = 1;
		c.gridy = 9;
		add(department, c);
		
		c.gridx = 1;
		c.gridy = 10;
		add(deptField, c);
		
		c.gridx = 2;
		c.gridy = 1;
		add(info, c);
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 3;
		c.anchor = GridBagConstraints.NORTHWEST;
		add(infoArea, c);
		
		c.gridx = 2;
		c.gridy = 5;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.WEST;
		add(tags, c);
		
		c.gridx = 2;
		c.gridy = 6;
		c.gridheight = 2;
		c.anchor = GridBagConstraints.NORTHWEST;
		add(tagsArea, c);
		
		c.gridx = 2;
		c.gridy = 8;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.WEST;
		add(img, c);
		
		c.gridx = 2;
		c.gridy = 9;
		add(imgField, c);
		
		c.gridx = 2;
		c.gridy = 10;
		add(save, c);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if(source.equals(save)){
				boolean success = true;
				try{
					save();
				} catch(IOException a){
					JOptionPane.showMessageDialog(getParent(), 
							"Could not open file " + imgField.getText(), 
							"File Not Found Error", JOptionPane.ERROR_MESSAGE);
					success = false;
				}
				if(success)
					exit();
			}
			else if(source.equals(cancel)){
				exit();
			}
		}
	}
}
