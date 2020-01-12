package nutritionalRecipe;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void calcNutritionFactsPerPortion() {
        Recipe myRecipe = new Recipe("myRecipe");
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        Ingredient ing1 = new Ingredient("chocolate", 60, "Grams");
        Ingredient ing2= new Ingredient("sugar", 1, "Cup");
        ing1.setCalories(100);
        ing2.setCalories(60);
        myRecipe.setPortions(4);
        assertEquals(myRecipe.getNutritionFacts().get(0), 40);
    }


}