package store.people;
import store.software.*;

public class Shopper extends User {
	
	public Shopper() {
		super();
		setPerms(new Permissions(true, false, false, false, false, false, false));
	}
	public Shopper(String username, String password) {
		super(username, password);
		setPerms(new Permissions(true, false, false, false, false, false, false));
	}
}
