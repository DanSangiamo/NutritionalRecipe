package nutritionalRecipe;

/**
 * This class allows the user to "store" the recipes in a "Recipe Box" which writes the recipe information to a CSV
 */

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecipeBox {
    private static final String CSV_FILENAME = "UserRecipes.csv";
    //private static RecipeBox box;
    private File userRecipeFile;

    /*
    public void init() throws IOException {
        createDatabaseFile();
        box = this;
    }

    public static RecipeBox getInstance() {
        return box;
    }
    */

    /**
     * creates csv file if one does not exist
     * @throws IOException
     */
    public void createRecipeFile() throws IOException {
        userRecipeFile = new File(CSV_FILENAME);
        if (!userRecipeFile.exists()) {
            userRecipeFile.createNewFile();
        }
    }

    /**
     * writes Recipe object to RecipeBox, stores information in csv
     * @param recipe
     */
    public void storeRecipes(Recipe recipe){
        try {
            FileWriter fw= new FileWriter(userRecipeFile, true);
            CSVWriter csvw = new CSVWriter(fw);
            String name=recipe.getName();
            String portion= "serves " + String.valueOf(recipe.getPortions()) + " people";
            String ingredients=recipe.toStringIngredients();
            String nutrition=recipe.nfToString();
            String[] recipeInfo ={name, portion, ingredients, nutrition};
            csvw.writeNext(recipeInfo);
            csvw.flush();
            csvw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
