package nutritionalRecipe;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecipeBox {
    //private ArrayList<Recipe> recipes;
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

    /*
    public RecipeBox(){
        recipes=new ArrayList<Recipe>();
    }


    public ArrayList<Recipe> getRecipes(){
        return recipes;
    }

    public void addRecipe(Recipe newRecipe){
        recipes.add(newRecipe);
    }

     */

    public void createDatabaseFile() throws IOException {
        userRecipeFile = new File(CSV_FILENAME);
        if (!userRecipeFile.exists()) {
            userRecipeFile.createNewFile();
        }
    }

    public void storeRecipes(Recipe recipe){
        try {
            FileWriter fw= new FileWriter(userRecipeFile, true);
            CSVWriter csvw = new CSVWriter(fw);
            String name=recipe.getName();
            String portion= "serves " + String.valueOf(recipe.getPortions()) + " people";
            String ingredients=recipe.toStringIng();
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
