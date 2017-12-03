package store.software;
import java.io.IOException;

import store.UI.GroceryGUI;
import store.objects.Department;
import store.objects.GroceryStore;
import store.objects.Product;

public class Main {

	public static void main(String[] args) {
		GroceryGUI window = new GroceryGUI();
		GroceryStore store = window.getStore();
		Product tomato = new Product();
		try{
			tomato.openImage("./res/tomato.png");
		} catch (IOException e){
			e.printStackTrace();
		}
		tomato.setName("Farmer Joe's Best Tomatoes");
		tomato.setIDNum(1);
		tomato.setPrice(5.00);
		tomato.setQuantity(500);
		tomato.addRating(new Rating(5, "Best tomatos in Canada!"));
		tomato.addRating(new Rating(3, "They're alright"));
		tomato.addRating(new Rating(2, "These tomatos are worse than BattleFront 2 (2017)"));
		tomato.setUnit("lb");
		Department produce = new Department();
		produce.setName("Produce");
		produce.getInventory().addProduct(tomato);
		store.addDepartment(produce);
	}

}
