package store.objects;

import java.util.ArrayList;
import store.*;

public class Department {
	private String name;
	private Inventory inventory;
	
	public Department() {
		name = "";
		inventory = new Inventory(this);
	}
	
	public void setName(String aName) {
		name = aName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setInventory(Inventory anInventory) {
		inventory = anInventory;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
}
