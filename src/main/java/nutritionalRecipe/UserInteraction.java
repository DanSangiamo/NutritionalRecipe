package nutritionalRecipe;

import java.util.HashMap;
import java.util.Scanner;

/**
 * This class handles the console interaction with the user
 */
public class UserInteraction {
    private String recipeName;
    private HashMap<String, Ingredient> listOfIngredients = new HashMap<String, Ingredient>();
    private int numberOfPortions;

    /**
     * This method asks the user for the ingredients in the recipe
     * 
     * @return a HashMap with the ingredients, amount and UOM
     */
    public HashMap<String, Ingredient> createRecipe() {
	NutritionApiCaller callApi = new NutritionApiCaller();

	String currentIngredient = "";// Every single ingredient
	Scanner in = new Scanner(System.in);

	System.out.println("Please enter the name of the recipe");
	recipeName = in.nextLine();
	System.out.println("How many servings/portions are in this recipe?");
	numberOfPortions = in.nextInt();
	in.nextLine();
	System.out.println("Now let's enter the ingredients and the quantities.");

	// Asks for ingredient name
	System.out.println("Enter the ingredient name (type END when done): ");
	currentIngredient = in.nextLine();
	Ingredient ing = new Ingredient(currentIngredient);

	// Start entering ingredients
	while (!currentIngredient.equals("END")) {

	    // Asks for UOM
	    System.out.println(
		    "Select the unit of measure:  Whole, Gram, Kilogram, Liter, Milliliter, Ounce, Pound, Pinch,\n                             "
			    + "Fluid Ounce, Gallon, Pint, Quart, Drop, Cup, Tablespoon, Teaspoon.");
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
	    ing.setUnitOfMeasure(uom);

	    // Asks for quantity
	    System.out.println("Enter the quantity: ");
	    double quantity = in.nextDouble();
	    in.nextLine();
	    ing.setAmount(quantity);

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
		System.out.println("Now it's fat turn. How much fat is in " + ing.getAmount() + " "
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

	    // Populates the HashMap;
	    listOfIngredients.put(currentIngredient, ing);

	    // Asks for next ingredient name
	    System.out.println("Enter the next ingredient name (type END when done): ");
	    currentIngredient = in.nextLine();
	    ing = new Ingredient(currentIngredient);
	}
	in.close();

	// Lists of ingredients
	System.out.println(
		"**********************************************************************************************");
	System.out.println("* These are the ingredients entered to prepared " + numberOfPortions + " portions of "
		+ recipeName + ". *");
	System.out.println("Ingredient Name\t\t\t\t\t\t Quantity");
	for (String key : listOfIngredients.keySet()) {
	    System.out.println(listOfIngredients.get(key).getName() + "\t\t\t\t\t\t"
		    + listOfIngredients.get(key).getAmount() + " " + listOfIngredients.get(key).getUnitOfMeasure());
	}
	System.out.println(
		"**********************************************************************************************");
	return listOfIngredients;
    }

    /**
     * This method asks the user to guess the calories per portion
     * 
     * @param recipe
     */
    public void guessCalories(Recipe recipe) {
	double caloriesPerPortion = (double) recipe.findCalories() / recipe.getPortions();
	int maxCalories = 0;
	String maxIngredient = "";

	// Calculates ingredient with highest calorie count
	for (String key : listOfIngredients.keySet()) {
	    if (listOfIngredients.get(key).getCalories() >= maxCalories) {
		maxCalories = listOfIngredients.get(key).getCalories();
		maxIngredient = listOfIngredients.get(key).getName();
	    }
	}

	// Asks user to guess the calories
	Scanner in = new Scanner(System.in);
	System.out.println("*************************************************************");
	System.out.println("Can you guess how many calories your recipe has per portion?.\nEnter the amount: ");
	double guessedCalories = in.nextDouble();
	in.hasNextLine();

	// Compares the answer with the correct answer +/- 2.5% calories
	double tolerance = caloriesPerPortion * 0.025;
	if (Math.abs(caloriesPerPortion - guessedCalories) > tolerance) {
	    System.out.println("You're off by more than 2.5%. The correct amount is: " + caloriesPerPortion
		    + ".\n The ingredient with the highest number of calories is " + maxIngredient + " with "
		    + maxCalories / numberOfPortions + " calories per portion.\nThat's "
		    + maxCalories / numberOfPortions / caloriesPerPortion
		    + "% of the total.\n We're going to look for a potentail subsitute.");
	} else {
	    System.out.println(
		    "That's great! Your guessed the correct amount within a 2.5% tolerance. The correct amount is: "
			    + caloriesPerPortion + ".\n The ingredient with the highest number of calories is "
			    + maxIngredient + " with " + maxCalories / numberOfPortions
			    + " calories per portion.\nThat's " + maxCalories / numberOfPortions / caloriesPerPortion
			    + "% of the total.\n We're going to look for a potentail subsitute.");
	}
	in.close();

    }
}
