package store.objects;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Product> products;
	private Department dept;

	public Inventory(Department dept) {
		products = new ArrayList<Product>();
		this.dept = dept;
	}
	
	public void addProduct(Product aProduct) {
		if(!products.contains(aProduct))
			products.add(aProduct);
		if(aProduct.getDepartment() == null || !aProduct.getDepartment().equals(dept)){
			aProduct.setDepartment(dept);
		}
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public boolean removeProduct(Product p){
		boolean toReturn = products.remove(p);
		if(toReturn)
			p.clearDepartment();
		return toReturn;
	}
}
