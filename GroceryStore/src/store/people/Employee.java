package store.people;
import store.software.*;

public class Employee extends User {
	
	public Employee() {
		super();
		setPerms(new Permissions(true, true, false, false, false, false, false));
	}

	public Employee(String userName, String password) {
		super(userName, password);
		setPerms(new Permissions(true, true, false, false, false, false, false));
	}
	
}