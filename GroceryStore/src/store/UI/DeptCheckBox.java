package store.UI;

import javax.swing.JCheckBox;

import store.objects.Department;

public class DeptCheckBox extends JCheckBox {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1261216152521649419L;
	private Department dept;
	
	public DeptCheckBox(String name){
		super(name);
	}
	
	public void setDept(Department newDept){
		dept = newDept;
	}
	
	public Department getDept(){
		return dept;
	}
}
