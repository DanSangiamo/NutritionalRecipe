package nutritionalRecipe;

import java.util.HashMap;
import java.util.Scanner;

/**
 * This class handles the console interaction with the user
 */
public class UserInteraction {
    private String recipeName;
    private HashMap<String, IngredientsAsObjects> ingredients = new HashMap<String, IngredientsAsObjects>();
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
        System.out.println("How many servings/portions are in this recipe?");
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
                    "Select the unit of measure:  Each, Gram, Kilogram, Liter, Milliliter, Ounce, Pound, Pinch, Fluid Ounce, Gallon, Pint, Quart, Drop, Cup, Tablespoon, Teaspoon");
            String uom = in.nextLine();

            // Validates UOM
            while (!uom.equals("Whole") && !uom.equals("Gram") && !uom.equals("Kilogram") && !uom.equals("Liter") && !uom.equals("Milliliter")
                    && !uom.equals("Ounce") && !uom.equals("Pound") && !uom.equals("Pinch") && !uom.equals("Fluid Ounce")
					&& !uom.equals("Gallon") && !uom.equals("Pint") && !uom.equals("Quart") && !uom.equals("Drop")
                    && !uom.equals("Cup") && !uom.equals("Tablespoon") && !uom.equals("Teaspoon")) {
                System.out.println("Incorrect entry. Please try again");
                System.out.println(
						"Select the unit of measure:  Whole, Gram, Kilogram, Liter, Milliliter, Ounce, Pound, Pinch, Fluid Ounce, Gallon, Pint, Quart, Drop, Cup, Tablespoon, Teaspoon");
                uom = in.nextLine();
            }

            /* Populates the HashMap */
			IngredientsAsObjects ing = new IngredientsAsObjects(ingredient, uom, quantity);
            ingredients.put(ingredient, ing);

            // Asks for ingredient name
            System.out.println("Enter the ingredient name: ");
            ingredient = in.nextLine();

        }
    }
}