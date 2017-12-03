package store.objects;
import store.software.Rating;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Product {
	private String name, unit;
	private int idNum, quantity;
	private ArrayList<Rating> userRatings;
	private ArrayList<String> tags;
	private double price;
	private BufferedImage img;
	public Product(){
		name = "";
		unit = "item";
		idNum = -1;
		quantity = 0;
		userRatings = new ArrayList<Rating>();
		tags = new ArrayList<String>();
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
	
	public void setUnit(String newName){
		unit = newName;
	}
	
	public String getUnit(){
		return unit;
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
	
	public void openImage(String path) throws IOException{
		img = ImageIO.read(new File(path));
	}
	
	public BufferedImage getImage(){
		return img;
	}
	
	public void addTag(String newTag){
		if(!tags.contains(newTag.toLowerCase())){
			tags.add(newTag.toLowerCase());
		}
	}
	
	public void removeTag(String tag){
		if(tags.contains(tag.toLowerCase())){
			tags.remove(tag.toLowerCase());
		}
	}
	
	public ArrayList<String> getTags(){
		return tags;
	}
}
