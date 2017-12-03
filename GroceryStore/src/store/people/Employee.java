package store.people;
import store.software.*;

public class Employee extends User {
	
	public Employee() {
		setPerms(new Permissions(true, true, false, false, false, false, false));
	}
	
}