package store.testers;

import store.people.User;
import store.software.Permissions;
import store.software.ShoppingCart;
import store.software.Rating;
import store.objects.Product;
import java.lang.Math;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testGettersSetters() {
		User person = new User();
		person.setName("John");
		person.setPassword("A very secure password");
		person.setPermissions(new Permissions(true, false, false, false, false, false, false));
		assert((person.getName() + " can shop: " + person.getPermissions().getPermission("shop")).equals("John can shop: true"));
		assert(person.getPassword().equals("A very secure password"));
	}
	
	@Test
	public void testAddRemoveCart(){
		User person = new User();
		Product apple = new Product();
		person.setName("Test person");
		apple.setIDNum(1);
		apple.setName("Canadian blue apple");
		apple.setPrice(5.00);
		apple.setQuantity(200);
		person.addToCart(apple, 25);
		assert(Math.abs(person.getCart().getTotal() - 125.0) < 0.001);
		person.removeFromCart(apple, 22);
		assert(Math.abs(person.getCart().getTotal() - 15.0) < 0.001);
		for(String action : person.getActions().getEvents()){
			System.out.println(action);
		}
	}

}
