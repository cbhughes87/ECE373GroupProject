package store.software;
import java.io.IOException;

import store.UI.GroceryGUI;
import store.objects.Department;
import store.objects.GroceryStore;
import store.objects.Product;
import store.people.Shopper;
import store.people.User;
import store.people.UserType;

public class Main {

	public static void main(String[] args) {
		GroceryStore store = new GroceryStore();
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
		tomato.setInfo("This is a vine-ripened tomato. All craftsdwarfship is of the highest quality. "
				+ "The sticker is colored red and orange, printed with the name of the farmer who grew this tomato. "
				+ "The tomato is adorned with leaves and a stem of green, and menaces with spikes of leaves.");
		tomato.addRating(new Rating(5, "Best tomatos in Canada!"));
		tomato.addRating(new Rating(3, "They're alright"));
		tomato.addRating(new Rating(2, "These tomatos are worse than BattleFront 2 (2017)"));
		tomato.setUnit("lb");
		tomato.addTag("fruit");
		Product potato = new Product();
		try{
			potato.openImage("./res/potato.png");
		} catch (IOException e){
			e.printStackTrace();
		}
		potato.setName("Billy Joe's Country Potatoes");
		potato.setIDNum(2);
		potato.setPrice(3.99);
		potato.setQuantity(5000);
		potato.addRating(new Rating(5, "I haven't tasted potatoes like these since the famine!"));
		potato.addRating(new Rating(5, "Worst game ever - IGN"));
		potato.addRating(new Rating(2, "Not enough ketchup"));
		potato.setUnit("lb");
		potato.addTag("tuber");
		Product pomegranate = new Product();
		try{
			pomegranate.openImage("./res/pomegranate.png");
		} catch (IOException e){
			e.printStackTrace();
		}
		pomegranate.setName("Hades' Devilishly Good Pomegranates");
		pomegranate.setIDNum(3);
		pomegranate.setPrice(6.99);
		pomegranate.setQuantity(200);
		pomegranate.addRating(new Rating(5, "These pomegranates remind me of the ones that trapped me in Tartarus"));
		pomegranate.addRating(new Rating(5, "Just like the ones from my mother's backyard"));
		pomegranate.addRating(new Rating(3, "Too difficult to open"));
		pomegranate.setUnit("lb");
		pomegranate.addTag("fruit");
		
		Product lettuce = new Product();
		try{
			lettuce.openImage("./res/lettuce.png");
		} catch (IOException e){
			e.printStackTrace();
		}
		lettuce.setName("Sank the Titanic Lettuce");
		lettuce.setIDNum(4);
		lettuce.setPrice(1.99);
		lettuce.setQuantity(300);
		lettuce.addRating(new Rating(4, "Salad is good for you, right?"));
		lettuce.addRating(new Rating(3, "Didn't come with ranch"));
		lettuce.addRating(new Rating(1, "I don't like lettuce"));
		lettuce.setUnit("head");
		lettuce.addTag("vegetable");
		
		Product orange = new Product();
		try{
			orange.openImage("./res/orange.png");
		} catch (IOException e){
			e.printStackTrace();
		}
		orange.setName("Florida Man Oranges");
		orange.setIDNum(5);
		orange.setPrice(0.99);
		orange.setQuantity(80);
		orange.addRating(new Rating(5, "These were the bomb!"));
		orange.addRating(new Rating(5, "An explosion of flavor in my mouth"));
		orange.addRating(new Rating(1, "Literally a hand grenade, would not recommend for human consumption"));
		orange.setUnit("lb");
		orange.addTag("organic");
		orange.addTag("fruit");
		
		Product steak = new Product();
		try{
			steak.openImage("./res/steak.png");
		} catch (IOException e){
			e.printStackTrace();
		}
		steak.setName("Cattleman Ranch Steaks");
		steak.setIDNum(8);
		steak.setPrice(5.00);
		steak.setQuantity(80);
		steak.addRating(new Rating(5, "Tender and well-marbled"));
		steak.addRating(new Rating(5, "Perfect for a romantic dinner"));
		steak.addRating(new Rating(1, "Charred beyond all recognition after dropped in fire"));
		steak.addRating(new Rating(5, "In Soviet Russia, steak burn YOU!"));
		steak.setUnit("lb");
		steak.addTag("manly");
		steak.addTag("red meat");
		
		Department produce = new Department();
		produce.setName("Produce");
		produce.getInventory().addProduct(tomato);
		produce.getInventory().addProduct(potato);
		produce.getInventory().addProduct(pomegranate);
		produce.getInventory().addProduct(lettuce);
		produce.getInventory().addProduct(orange);
		
		Department meat = new Department();
		meat.setName("Meat");
		meat.getInventory().addProduct(steak);
		
		store.addUser("ryan", "password", UserType.ADMIN);
		store.addDepartment(produce);
		store.addDepartment(meat);
		User ryan = store.getUser("ryan", "password");
		ryan.addToCart(pomegranate, 5);
		ryan.addToCart(steak, 5);
		
		GroceryGUI window = new GroceryGUI(store);
	}

}
