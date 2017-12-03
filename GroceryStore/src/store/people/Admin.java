package store.people;
import store.software.*;

public class Admin extends User {
	
	public Admin() {
		super();
		setPerms(new Permissions(true, true, true, true, true, true, true));
	}

	public Admin(String userName, String password) {
		super(userName, password);
		setPerms(new Permissions(true, true, true, true, true, true, true));
	}
	
}