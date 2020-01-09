package nutritionalRecipe;

/**
 * this class represents a Recipe object that includes a list of ingredients, a specified number of servings, and the nutrition facts
 * of one portion
 */

import java.io.IOException;
import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private int numPortions;
    private ArrayList<Double> nutritionFacts;

    public Recipe(String name){
        ingredients=new ArrayList<Ingredient>();
        int numPortions;
        this.name=name;
        nutritionFacts=new ArrayList<Double>();
    }

    public String getName() {
        return name;
    }

    public void setPortions(int numPortions){
        this.numPortions = numPortions;
    }

    public int getPortions() {
        return numPortions;
    }

    /**
     * gets list of ingredients
     * @return
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * adds ingredient to recipe
     * @param newIngredient
     */
    public void addIngredient(Ingredient newIngredient){
        ingredients.add(newIngredient);
    }

    /**
     * calculate nutrition facts of one portion of the recipe
     */
    public void calcNutritionFactsPerPortion(){
        double totalCalories=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalCalories += (ingredients.get(i).getCalories()/numPortions;
        }
        nutritionFacts.add(totalCalories);

        double totalGramsFat=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalGramsFat+= (ingredients.get(i).getFat()/numPortions);
        }
        nutritionFacts.add(totalGramsFat);

        double totalProtein=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalProtein+= (ingredients.get(i).getProtein()/numPortions);
        }
        nutritionFacts.add(totalProtein);

        double totalCarbohydrates=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalCarbohydrates+= (ingredients.get(i).getCarbs()/numPortions);
        }
        nutritionFacts.add(totalCarbohydrates);

        double totalSugar=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalSugar+= (ingredients.get(i).getSugar()/numPortions);
        }
        nutritionFacts.add(totalSugar);

        double totalFiber=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalFiber+= (ingredients.get(i).getFiber());
        }
        nutritionFacts.add(totalFiber);

    }

    /**
     * converts recipe with ingredients to String
     * @return
     */
    public String toStringRecipe(){
        String ingListStr=" ";
        for(int i=0; i<ingredients.size(); i++){
            ingListStr+=ingredients.get(i).toString()+ " ";
        }
        return name + " serves " + getPortions() + " people," + ingListStr;
    }

    /**
     * converts nutrition facts to String
     * @return
     */
    public String nfToString(){
        return "calories:" + nutritionFacts.get(0) + ", "+ "fat:" + nutritionFacts.get(1) + "g, protein:" + nutritionFacts.get(2) +"g, carbohydrates:"
                + nutritionFacts.get(3)+ "g, sugar:" + nutritionFacts.get(4) + "g, fiber:" + nutritionFacts.get(5) + "g";
    }


}
