package store.people;
import store.objects.Product;
import store.software.ShoppingCart;
import store.software.Log;
import store.software.Permissions;

public class User {
	private String name;
	private String password; //Obviously not an actual program...
	
	private ShoppingCart cart;
	private Log actions;
	private Permissions perms;
	
	public User(){
		name = "";
		password = "";
		perms = new Permissions(false, false, false, false, false, false, false);
		cart = new ShoppingCart();
		actions = new Log();
	}
	
	public User(String username, String pass){
		name = username;
		password = pass;
		perms = new Permissions(false, false, false, false, false, false, false);
		cart = new ShoppingCart();
		actions = new Log();
		actions.setName("Log for user: " + name);
	}
	
	public void setName(String newName){
		name = newName;
		actions.setName("Log for user: " + name);
	}
	
	public String getName(){
		return name;
	}
	
	public void setPassword(String newPass){
		password = newPass;
	}
	
	public String getPassword(){
		return password;
	}
	
	public ShoppingCart getCart(){
		return cart;
	}
	
	public void addToCart(Product p, int amt){
		cart.addProduct(p, amt);
		actions.addEvent(name + " added " + amt + " " + p.getName() + "(s) to their cart.\n\tTheir cart now has a total cost of $" + String.format("%.2f", cart.getTotal()));
	}
	
	public void removeFromCart(Product p, int amt){
		cart.removeProduct(p, amt);
		actions.addEvent(name + " removed " + amt + " " + p.getName() + "(s) from their cart.\n\tTheir cart now has a total cost of $" + String.format("%.2f", cart.getTotal()) + "\n");
	}
	
	public Log getActions(){
		return actions;
	}
	
	public Permissions getPermissions(){
		return perms;
	}
	
	public void setPermissions(Permissions newPerms){
		perms = newPerms;
	}
}
