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
		pomegranate.setInfo("Used to lure hapless Greek goddesses for the last 30 centuries, "
				+ "Hades' Devilishly Good Pomegranates are the best fruit on the market to get "
				+ "yourself a deity for a wife.");
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
		orange.setPrice(0.99);
		orange.setQuantity(80);
		orange.setInfo("Florida Man makes orange into hand grenade after alligator traps him in his backyard.");
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
		steak.setPrice(5.00);
		steak.setQuantity(80);
		steak.addRating(new Rating(5, "Tender and well-marbled"));
		steak.addRating(new Rating(5, "Perfect for a romantic dinner"));
		steak.addRating(new Rating(1, "Charred beyond all recognition after dropped in fire"));
		steak.addRating(new Rating(5, "In Soviet Russia, steak burn YOU!"));
		steak.setUnit("lb");
		steak.addTag("manly");
		steak.addTag("red meat");
		
		Product lemons = new Product();
		try{
			lemons.openImage("./res/lemon.png");
		} catch (IOException e){
			e.printStackTrace();
		}
		lemons.setName("Cave Johnson's Signature Organic Lemons");
		lemons.setPrice(50.00);
		lemons.setQuantity(10);
		lemons.setInfo("When life gives you lemons, don't make lemonade. Make life TAKE THE LEMONS BACK! "
				+ "GET MAD! I DON'T WANT YOUR DAMN LEMONS! WHAT AM I SUPPOSED TO DO WITH THESE!? "
				+ "DEMAND TO SEE LIFE'S MANAGER! MAKE LIFE RUE THE DAY IT THOUGHT IT COULD GIVE "
				+ "CAVE JOHNSON LEMONS!! DO YOU KNOW WHO I AM!?! I'M THE MAN WHO'S GONNA BURN YOUR HOUSE DOWN... "
				+ "With THE LEMONS! I'M GONNA GET MY ENGINEERS TO INVENT A COMBUSTIBLE LEMON THAT BURNS YOUR HOUSE DOWN!");
		lemons.addRating(new Rating(5, "I'm Cave Johnson, and I approve of these lemons. "
				+ "Given directly by Life itself, they are "
				+ "perfect for burning your enemy's house down"));
		/*lemons.addRating(new Rating(5, "Very useful as lemon stealing wh... Oh, what's that? "
				+ "We aren't allowed to say that word on the internet any more? Well then. *ahem* "
				+ "These lemons are very useful as lemon stealing lady-of-the-night repellant."));*/
		lemons.addRating(new Rating(5, "Crashed and burned... PERFECT!"));
		lemons.addRating(new Rating(5, "Tried to use these lemons in my iced tea, ended up with tea all over my house "
				+ "and down a hand. 10/10 would explode again!"));
		lemons.setUnit("grenade");
		lemons.addTag("manly");
		lemons.addTag("Organic");
		lemons.addTag("Big boom");
		
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
		
		Department ordnance = new Department();
		ordnance.setName("Ordnance");
		ordnance.getInventory().addProduct(lemons);
		
		store.addUser("ryan", "password", UserType.ADMIN);
		store.addUser("james", "password", UserType.ADMIN);
		store.addUser("chris", "password", UserType.ADMIN);
		store.addUser("xlemons", "HighInVitaminC4", UserType.SHOPPER);
		store.addDepartment(produce);
		store.addDepartment(meat);
		store.addDepartment(ordnance);
		User ryan = store.getUser("ryan", "password");
		ryan.addToCart(pomegranate, 5);
		ryan.addToCart(steak, 5);
		
		GroceryGUI window = new GroceryGUI(store);
	}

}
