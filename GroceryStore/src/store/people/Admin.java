package store.people;
import store.software.*;

public class Admin extends User {
	
	public Admin() {
		setPerms(new Permissions(true, true, true, true, true, true, true));
	}
	
}