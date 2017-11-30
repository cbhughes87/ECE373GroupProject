package store.people;
import store.objects.Product;
import store.software.ShoppingCart;
import store.software.Log;
import store.software.Permissions;

public abstract class User {
	private String name;
	private String password; //Obviously not an actual program...
	
	private ShoppingCart cart;
	private Log actions;
	private Permissions perms;
	
	public User() {
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
	}
	
	public void setName(String newName){
		name = newName;
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
	}
	
	public Log getActions(){
		return actions;
	}
	
//	public abstract Permissions getPermissions();	
//	public abstract void setPermissions(Permissions newPerms);

	public Permissions getPerms() {
		return perms;
	}

	public void setPerms(Permissions perms) {
		this.perms = perms;
	}

}
