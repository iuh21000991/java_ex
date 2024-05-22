package fit.iuh.thuan;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.ItemDAO;
import entity.Food;
import implement.ItemImplement;

class ItemImplementTest {

	private static ItemDAO itemDAO;

	@BeforeAll 
	static void setUpBeforeClass() throws Exception {
		itemDAO = new ItemImplement();
	}
	
	@Test
	void testAddFood() {
		Food food = new Food();
		food.setId("F002");
		food.setName("Pizza");
		food.setPrice(10);
		food.setPreparationTime(20);
		food.setServingTime(30);
		food.setType(Food.Type.MAIN_COURSE);
		food.setOnSpecial(true);
		food.setIngredients(null);
		assertTrue(itemDAO.addFood(food));
		
	}
	
	@Test
	void testAddFoodFail() {
		Food food = new Food();
		food.setId("F002");
		food.setName("Pizza");
		food.setPrice(10);
		food.setPreparationTime(20);
		food.setServingTime(30);
		food.setType(Food.Type.MAIN_COURSE);
		food.setOnSpecial(true);
		food.setIngredients(null);
		assertTrue(!itemDAO.addFood(food));

	}
	
	@Test
	void testListItems() {
		assertTrue(itemDAO.listItems("An").size() > 0);
	}
	
	@Test
	void testListFoodAndCost() {
		assertTrue(itemDAO.listFoodAndCost().size() > 0);
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		itemDAO = null;
	}


	
}
