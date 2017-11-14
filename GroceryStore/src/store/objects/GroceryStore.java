package store.objects;

import java.util.ArrayList;
import store.software.*;

public class GroceryStore {
	private ArrayList<Department> departments;
	private ArrayList<Log> logs;
	
	public GroceryStore() {
		departments = new ArrayList<Department>();
		logs = new ArrayList<Log>();
	}
	
	public void addDepartment(Department newDepartment) {
		departments.add(newDepartment);
		// include error/duplicate checking
	}
	
	//possibly make bool to determine successful removal? or display error message if otherwise
	public void removeDepartment(String name) {
		for (int i = 0; i < departments.size(); i = i + 1) {
			if (departments.get(i).getName().equals(name)){
				departments.remove(i);
				return;
			}
		}
	}
	
	public ArrayList<Department> getDepartments() {
		return departments;
	}
	
	public void addLog(Log newLog) {
		logs.add(newLog);
	}
	public ArrayList<Log> getLogs() {
		return logs;
	}
	
	
	
}
