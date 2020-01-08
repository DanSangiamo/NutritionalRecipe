package nutritionalRecipe;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UserInteractionTest {

    @Test
    void testGuessCalories() {
	
	
	/*private String name;
	private double amount;
	private String unitOfMeasure;
	private int calories;
	private int fat;
	private int protein;
	private int carbs;
	private int sugar;
	private int fiber;
	*/
	
	UserInteraction ui = new UserInteraction();
	Recipe breadRecipe = new Recipe("Bread");
	ArrayList <Ingredient> ingredients = new ArrayList<>();
	Ingredient flour = new Ingredient("White Flour", 450, "Gram");
	Ingredient water = new Ingredient("Water", 1.75, "Cup");
	Ingredient butter = new Ingredient("Butter", 3, "Tablespoon");
	breadRecipe.addIngredient(flour);
	breadRecipe.addIngredient(water);
	breadRecipe.addIngredient(butter);
	ui.guessCalories(breadRecipe);
	
    }

}
