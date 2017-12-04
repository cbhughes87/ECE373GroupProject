package store.software;
import java.util.HashMap;
import java.util.ArrayList;
import store.objects.Product;

public class ShoppingCart {
	private HashMap<Product, Double> products;
	private double total;
	
	public ShoppingCart(){
		products = new HashMap<Product, Double>();
		total = 0;
	}
	
	public void removeAll(String message){
		
		for(Product key : getProducts()){
			if(message.equals("Purchased items.")){
				key.decrement(products.get(key));
			}
			products.remove(key);
		}
		total = 0;
	}
	
	
	public void addProduct(Product toAdd){
		if(products.containsKey(toAdd))
			products.put(toAdd, products.get(toAdd) + 1);
		else
			products.put(toAdd, 1.0);
		total += toAdd.getPrice();
	}
	
	public void addProduct(Product toAdd, int quantity){
		if(products.containsKey(toAdd))
			products.put(toAdd, products.get(toAdd) + quantity);
		else
			products.put(toAdd, (double)quantity);
		total += toAdd.getPrice() * quantity;
	}
	
	public void addProduct(Product toAdd, double quantity){
		if(products.containsKey(toAdd))
			products.put(toAdd, products.get(toAdd) + quantity);
		else
			products.put(toAdd, quantity);
		total += toAdd.getPrice() * quantity;
	}
	
	public void removeProduct(Product target){
		if(!products.containsKey(target))
			return;
		double prevVal = products.get(target);
		products.put(target, products.get(target) - 1);
		if(products.get(target) <= 0){
			products.remove(target);
			total -= target.getPrice() * prevVal;
		}
		else
			total -= target.getPrice();
	}
	
	public void removeProduct(Product target, int quantity){
		if(!products.containsKey(target))
			return;
		double prevVal = products.get(target);
		products.put(target, prevVal - quantity);
		if(products.get(target) <= 0.00001){
			products.remove(target);
			total -= target.getPrice() * prevVal;
		}
		else
			total -= target.getPrice() * quantity;
	}
	
	public void removeProduct(Product target, double quantity){
		if(!products.containsKey(target))
			return;
		double prevVal = products.get(target);
		products.put(target, prevVal - quantity);
		if(products.get(target) <= 0.00001){
			products.remove(target);
			total -= target.getPrice() * prevVal;
		}
		else
			total -= target.getPrice() * quantity;
	}
	
	public ArrayList<Product> getProducts(){
		ArrayList<Product> toReturn = new ArrayList<Product>();
		for(Product p : products.keySet()){
			toReturn.add(p);
		}
		return toReturn;
	}
	
	public double getQuantity(Product p){
		return products.get(p);
	}
	
	public double getTotal(){
		return total;
	}
	
	public String getViewString(){
		StringBuilder builder = new StringBuilder("View Cart");
		if(products.isEmpty()){
			return builder.toString();
		}
		builder.append(" (" + products.size() + ")");
		return builder.toString();
	}
}
