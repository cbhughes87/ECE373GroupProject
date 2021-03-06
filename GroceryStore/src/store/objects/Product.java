package store.objects;
import store.software.Rating;
import java.util.ArrayList;

public class Product {
	private String name;
	private int idNum, quantity;
	private ArrayList<Rating> userRatings;
	private double price;
	
	public Product(){
		name = "";
		idNum = -1;
		quantity = 01;
		userRatings = new ArrayList<Rating>();
		price = -1;
	}
	
	public double averageRating(){
		if(userRatings.size() == 0)
			return -1;
		double sum = 0;
		for(Rating r : userRatings){
			sum += r.getScore();
		}
		return sum / userRatings.size();
	}
	
	public boolean decrement(){
		if(quantity > 0){
			quantity -= 1;
			return true;
		}
		return false;
	}
	
	public boolean decrement(int amount){
		if(quantity >= amount && amount >= 0){
			quantity -= amount;
			return true;
		}
		return false;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public String getName(){
		return name;
	}
	
	public void setIDNum(int num){
		idNum = num;
	}
	
	public int getIDNum(){
		return idNum;
	}
	
	public void setQuantity(int newQuantity){
		quantity = newQuantity;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void addRating(Rating newRating){
		userRatings.add(newRating);
	}
	
	public ArrayList<Rating> getRatings(){
		return userRatings;
	}
	
	public void setPrice(double newPrice){
		price = newPrice;
	}
	
	public double getPrice(){
		return price;
	}
}
