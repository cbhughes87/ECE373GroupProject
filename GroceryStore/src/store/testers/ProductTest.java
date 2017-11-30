package store.testers;

import static org.junit.Assert.*;

import org.junit.Test;

import store.objects.Product;
import store.software.Rating;
import java.lang.Math;

public class ProductTest {

	@Test
	public void testRatings() {
		Product apple = new Product();
		apple.setName("This is an apple");
		apple.setIDNum(1);
		apple.setPrice(5.99);
		apple.setQuantity(200);
		apple.addRating(new Rating(5.0, "BEST APPLE EVER!"));
		apple.addRating(new Rating(4.0, "Only slightly better than the iPhone X"));
		apple.addRating(new Rating(3.0, "Too sweet"));
		apple.addRating(new Rating(5.0, "Repetitive and boring - IGN"));
		assert(Math.abs(apple.averageRating() - 4.25) < 0.001);
		apple.addRating(new Rating(5.0, "BEST APPLE EVER!"));
		apple.addRating(new Rating(4.0, "Only slightly better than the iPhone X"));
		assert(Math.abs(apple.averageRating() - 4.3333) < 0.001);
	}

}
