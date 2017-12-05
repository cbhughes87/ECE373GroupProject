package store.objects;

import java.util.ArrayList;

import store.people.Admin;
import store.people.Employee;
import store.people.Shopper;
import store.people.User;
import store.people.UserType;
import store.software.*;


public class GroceryStore {
	public static final double TAXRATE = 0.092;
	private ArrayList<Department> departments;
	private ArrayList<User> users;
	private ArrayList<Log> logs;
	
	private User currUser;
	private Log errLog;
	
	public GroceryStore() {
		departments = new ArrayList<Department>();
		users = new ArrayList<User>();
		logs = new ArrayList<Log>();
		currUser = null;
		errLog = new Log();
		errLog.setName("Store Error Log");
		logs.add(errLog);
	}
	
	public Product[] getAllProducts(){
		ArrayList<Product> prods = new ArrayList<Product>();
		for(Department dept : departments){
			for(Product p : dept.getInventory().getProducts()){
				prods.add(p);
			}
		}
		return prods.toArray(new Product[1]);
	}
	
	public void setCurrUser(User u){
		currUser = u;
	}
	
	public User getCurrUser(){
		return currUser;
	}
	
	public void addDepartment(Department newDepartment) {
		if(departments.contains(newDepartment)){
			errLog.addEvent("Error: duplicate department " + newDepartment.getName() + " ignored.");
			return;
		}
		departments.add(newDepartment);
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
	
	public String addUser(String userName, String password, UserType type){
		User toAdd;
		if(userName.equals("")){
			return "Username must have a value!";
		} else if(password.length() < 8){
			return "Password must be at least 8 characters long!";
		}
		for(User u : users){
			if(u.getName().equals(userName)){
				return "Username already exists!";
			}
		}
		switch(type){
		case SHOPPER:
			toAdd = new Shopper(userName, password);
			break;
		case ADMIN:
			toAdd = new Admin(userName, password);
			break;
		case EMPLOYEE:
			toAdd = new Employee(userName, password);
			break;
		default:
			toAdd = null;
			break;
		}
		if(toAdd == null){
			return "Could not add user: " + userName;
		}
		users.add(toAdd);
		return "";
	}
	
	public User getUser(String userName, String password){
		for(User u : users){
			if(u.getName().equals(userName) && u.getPassword().equals(password)){
				return u;
			}
		}
		return null;
	}
	
}
