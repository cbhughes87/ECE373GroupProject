package store.objects;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Product> products;

	public Inventory() {
		products = new ArrayList<Product>();
	}
	
	public void addProduct(Product aProduct) {
		products.add(aProduct);
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public void updateProduct(Product aProduct) {
		//slightly messy implementation. Maybe check for a product with identical name or ID then update remaining info?
		for(Product prod : products){
		
		}
	}
}
