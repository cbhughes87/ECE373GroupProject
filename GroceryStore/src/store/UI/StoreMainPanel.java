package store.UI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import store.objects.Department;
import store.objects.GroceryStore;
import store.objects.Product;

public class StoreMainPanel extends JPanel {
	private JScrollPane products;
	private JTextField search;
	private JPanel filters, departments;
	private GroceryStore store;
	private ArrayList<ActionListener> actionListeners;
	
	private ArrayList<String> filterTags;
	private ArrayList<Department> deptWhitelist;
	private String searchText;
	
	public StoreMainPanel(GroceryStore store){
		this.store = store;
		actionListeners = new ArrayList<ActionListener>();
		filterTags = new ArrayList<String>();
		deptWhitelist = new ArrayList<Department>();
		search = new JTextField("Search...");
		search.setPreferredSize(new Dimension(100, 20));
		searchText = null;
		search.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e) {
				if(search.getText().trim().equals("Search...")){
					search.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if(search.getText().trim().equals("")){
					search.setText("Search...");
				}
			}
		});
		search.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		search.getActionMap().put("enter", new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
				searchText = search.getText().toLowerCase();
				if(searchText.equals("") || searchText.equals("Search..."))
					searchText = null;
				searchAndFilter();
			}
		});
		refreshGraphics();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		add(departments, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		add(filters, c);
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		add(search, c);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		add(products, c);
	}
	
	private void searchAndFilter(){
		GridBagConstraints c = ((GridBagLayout)getLayout()).getConstraints(products);
		Dimension currDim = products.getPreferredSize();
		remove(products);
		createProductsPane();
		searchText = null;
		products.setPreferredSize(currDim);
		add(products, c);
		revalidate();
		repaint();
		for(ActionListener a : actionListeners){
			a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "refresh"));
		}
	}
	
	public void addActionListener(ActionListener a){
		if(!actionListeners.contains(a)){
			actionListeners.add(a);
		}
	}
	
	public boolean removeActionListener(ActionListener a){
		return actionListeners.remove(a);
	}
	
	public void refreshGraphics(){
		createProductsPane();
		createFilters();
		createDepartments();
		revalidate();
		repaint();
	}
	
	private void createProductsPane(){
		JPanel tempProducts = new JPanel();
		tempProducts.setLayout(new BoxLayout(tempProducts, BoxLayout.Y_AXIS));
		ArrayList<Department> depts;
		if(deptWhitelist.isEmpty())
			depts = store.getDepartments();
		else
			depts = deptWhitelist;
		for(Department dept : depts){
			tempProducts.add(new JLabel(dept.getName()));
			for(Product prod : dept.getInventory().getProducts()){
				SmallProductPanel toAdd = new SmallProductPanel(prod, 175, 125);
				toAdd.setAlignmentY(TOP_ALIGNMENT);
				if(!prod.getTags().isEmpty() && !filterTags.isEmpty()){
					boolean addProd = true;
					for(String tag : filterTags){
						addProd &= prod.getTags().contains(tag);
						if(!addProd)
							break;
					}
					if(addProd){
						if(searchText == null)
							tempProducts.add(toAdd);
						else if(prod.getName().toLowerCase().contains(searchText))
							tempProducts.add(toAdd);
					}
				}
				else if(filterTags.isEmpty() && searchText == null){
					tempProducts.add(toAdd);
				}
				else if(filterTags.isEmpty() && searchText != null){
					if(prod.getName().toLowerCase().contains(searchText)){
						tempProducts.add(toAdd);
					}
				}
					
			}
		}
		products = new JScrollPane(tempProducts);
		products.setPreferredSize(new Dimension(products.getPreferredSize().width + 15, 500));
		products.getVerticalScrollBar().setUnitIncrement(15);
	}
	
	private void createFilters(){
		filters = new JPanel();
		filters.setLayout(new BoxLayout(filters, BoxLayout.Y_AXIS));
		filters.add(new JLabel("Filters"));
		try{
			Scanner tags = new Scanner(new File("./res/tags.txt"));
			while(tags.hasNextLine()){
				JCheckBox checkbox = new JCheckBox(tags.nextLine());
				checkbox.addActionListener(new FilterListener());
				checkbox.setAlignmentX(LEFT_ALIGNMENT);
				filters.add(checkbox);
			}
			tags.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
	}
	
	private void createDepartments(){
		departments = new JPanel();
		departments.setLayout(new BoxLayout(departments, BoxLayout.Y_AXIS));
		departments.add(new JLabel("Departments"));
		for(Department d : store.getDepartments()){
			DeptCheckBox toAdd = new DeptCheckBox(d.getName());
			toAdd.setDept(d);
			toAdd.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					DeptCheckBox clicked = (DeptCheckBox)e.getSource();
					if(clicked.isSelected())
						deptWhitelist.add(clicked.getDept());
					else
						deptWhitelist.remove(clicked.getDept());
					searchAndFilter();
				}
			});
			departments.add(toAdd);
		}
	}
	
	private class FilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JCheckBox clicked = (JCheckBox)e.getSource();
			if(clicked.isSelected())
				filterTags.add(clicked.getText().toLowerCase());
			else
				filterTags.remove(clicked.getText().toLowerCase());
			searchAndFilter();
		}
	}
}
