package nutritionalRecipe;

import java.util.HashMap;
import java.util.Scanner;

public class UserInteraction {
    private String recipeName;
    private HashMap <String, Integer> ingredients = new HashMap<String, Integer>();
    private double ingredientQuantity;
    private int numberOfPortions;
    private double guessedCalories;
    private double realCalories;

    public void createRecipe() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the name of the recipe");
        recipeName = in.nextLine();
        System.out.println("Now let's enter the ingredients and the quantities. When done, please type END");

    }
}
