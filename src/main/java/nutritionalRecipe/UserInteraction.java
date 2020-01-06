package nutritionalRecipe;

import java.util.HashMap;
import java.util.Scanner;

/**
 * This class handles the console interaction with the user
 * 
 * @author juangoleniowski
 *
 */
public class UserInteraction {
    private String recipeName;
    private HashMap<String, Double> ingredients = new HashMap<String, Double>();
    private double ingredientQuantity;
    private int numberOfPortions;
    private double guessedCalories;
    private double realCalories;

    /**
     * This method asks the user for the ingredients in the recipe
     */
    public void createRecipe() {
	String ingredient = "";// Every single ingredient
	Scanner in = new Scanner(System.in);
	System.out.println("Please enter the name of the recipe");
	recipeName = in.nextLine();
	System.out.println("How many servings/portings are in this recipe?");
	numberOfPortions = in.nextInt();
	in.nextLine();
	System.out.println("Now let's enter the ingredients and the quantities. When done, please type END");

	// Asks for ingredient name
	System.out.println("Enter the ingredient name: ");
	ingredient = in.nextLine();
	// Maybe include here the call to the ingredient class?

	while (!ingredient.equals("END")) {
	    // Asks for quantity
	    System.out.println("Enter the quantity: ");
	    double quantity = in.nextDouble();
	    in.nextLine();

	    // Asks for UOM
	    System.out.println(
		    "Select the unit of measure E(each), G(grams), M(Mililiters), C(cups), T(tablespoon), t(teaspoon) or O(ounces)");
	    String uom = in.nextLine();

	    // Validates UOM
	    while (!uom.equals("E") && !uom.equals("G") && !uom.equals("M") && !uom.equals("C") && !uom.equals("T")
		    && !uom.equals("t") && !uom.equals("O")) {
		System.out.println("Incorrect entry. Please try again");
		System.out.println(
			"Select the unit of measure E(each), G(grams),M(Mililiters) C(cups), T(tablespoon), t(teaspoon) or O(ounces)");
		uom = in.nextLine();
	    }

	    // Populates the HashMap
	    ingredients.put(ingredient, quantity);

	}
    }
}