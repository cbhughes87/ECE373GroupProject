package store.people;
import store.software.*;

public class Shopper extends User {
	
	public Shopper() {
		setPerms(new Permissions(true, false, false, false, false, false, false));
	}
}
