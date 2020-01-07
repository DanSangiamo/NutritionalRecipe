package nutritionalRecipe;

import java.util.HashMap;
import java.util.Scanner;

/**
 * This class handles the console interaction with the user
 */
public class UserInteraction {
    private String recipeName;
    private HashMap<String, Ingredient> recipe = new HashMap<String, Ingredient>();
    private double ingredientQuantity;
    private int numberOfPortions;
    private double guessedCalories;
    private double realCalories;

    /**
     * This method asks the user for the ingredients in the recipe
     */
    public void createRecipe() {
	NutritionApiCaller callApi = new NutritionApiCaller();
	
	String currentIngredient = "";// Every single ingredient
	Scanner in = new Scanner(System.in);

	System.out.println("Please enter the name of the recipe");
	recipeName = in.nextLine();
	System.out.println("How many servings/portions are in this recipe?");
	numberOfPortions = in.nextInt();
	in.nextLine();
	System.out.println("Now let's enter the ingredients and the quantities. When done, please type END");

	// Asks for ingredient name
	System.out.println("Enter the ingredient name: ");
	currentIngredient = in.nextLine();
	Ingredient ing = new Ingredient(currentIngredient);
	
	// Start entering ingredients
	while (!currentIngredient.equals("END")) {
	    // Create ingredient as object

	    // Validate ingredient
	    int ingExists = callApi.makeNutritionalCall(ing);

	    while (ingExists == -1) {
		System.out.println("I can't find the ingredient. Please check the spelling. You entered: \""
			+ currentIngredient + "\" \nIs this the correct name? (Y/N)");
		String answer = in.nextLine();
		if (answer.equals("N")) {
		    System.out.println("Enter the ingredient name: ");
		    currentIngredient = in.nextLine();
		    ingExists = callApi.makeNutritionalCall(ing);
		} else if (answer.equals("Y")) {
		    ingExists = 2;
		} else {
		    System.out.println("Incorrect entry. Please try again.");
		}

	    }

	    // Asks for UOM
	    System.out.println(
		    "Select the unit of measure:  Each, Gram, Kilogram, Liter, Milliliter, Ounce, Pound, Pinch, Fluid Ounce, Gallon, Pint, Quart, Drop, Cup, Tablespoon, Teaspoon");
	    String uom = in.nextLine();

	    // Validates UOM
	    while (!uom.equals("Whole") && !uom.equals("Gram") && !uom.equals("Kilogram") && !uom.equals("Liter")
		    && !uom.equals("Milliliter") && !uom.equals("Ounce") && !uom.equals("Pound") && !uom.equals("Pinch")
		    && !uom.equals("Fluid Ounce") && !uom.equals("Gallon") && !uom.equals("Pint")
		    && !uom.equals("Quart") && !uom.equals("Drop") && !uom.equals("Cup") && !uom.equals("Tablespoon")
		    && !uom.equals("Teaspoon")) {
		System.out.println("Incorrect entry. Please try again");
		System.out.println(
			"Select the unit of measure:  Whole, Gram, Kilogram, Liter, Milliliter, Ounce, Pound, Pinch, Fluid Ounce, Gallon, Pint, Quart, Drop, Cup, Tablespoon, Teaspoon");
		uom = in.nextLine();
	    }

	    // Asks for quantity
	    System.out.println("Enter the quantity: ");
	    double quantity = in.nextDouble();
	    in.nextLine();

	    // This lines only execute if the ingredient is new
	    if (ingExists == 2) {
		System.out.println(
			"**************************************************************************************\n"
				+ "You have entered a new ingredint.\nIn order to calculate the nutrional value of the recipe, "
				+ "we need a bit more information.\n");
		System.out.println("Let's start with calories. How many calories are in " + ing.getAmount() + " "
			+ ing.getUnitOfMeasure() + " of " + ing.getName());
		ing.setCalories(in.nextInt());
		in.nextLine();
		System.out.println("Then, let's look at carbs. How many carbs are in " + ing.getAmount() + " "
			+ ing.getUnitOfMeasure() + " of " + ing.getName());
		ing.setCarbs(in.nextInt());
		in.nextLine();
		System.out.println("Now it's fat turns. How much fat is in " + ing.getAmount() + " "
			+ ing.getUnitOfMeasure() + " of " + ing.getName());
		ing.setFat(in.nextInt());
		in.nextLine();
		System.out.println("How about proteins?. How much protein is in " + ing.getAmount() + " "
			+ ing.getUnitOfMeasure() + " of " + ing.getName());
		ing.setProtein(in.nextInt());
		in.nextLine();
		System.out.println("And sugar?. How much sugar is in " + ing.getAmount() + " " + ing.getUnitOfMeasure()
		+ " of " + ing.getName());
		ing.setSugar(in.nextInt());
		in.nextLine();
		System.out.println("And finally fibers. How much fiber is in " + ing.getAmount() + " "
			+ ing.getUnitOfMeasure() + " of " + ing.getName());
		ing.setFiber(in.nextInt());
		in.nextLine();
	    }
	}

	// Populates the HashMap;
	recipe.put(currentIngredient, ing);
	
	// Asks for ingredient name
	System.out.println("Enter the ingredient name: ");
	currentIngredient = in.nextLine();

    }
}


