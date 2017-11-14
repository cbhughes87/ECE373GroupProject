package store.software;

import java.util.ArrayList;

public class Log {
	String name;
	ArrayList<String> events;
	
	public Log() {
		name = "";
		events = new ArrayList<String>();
	}
	
	public void setName(String aName) {
		name = aName;
	}
	public String getName() {
		return name;
	}
	public void addEvent(String event) {
		events.add(event);
	}
	public ArrayList<String> getEvents() {
		return events;
	}
}
