package nutritionalRecipe;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class RecipeBox {
    private ArrayList<Recipe> recipes;
    private static final String CSV_FILENAME = "UserRecipes.csv";
    private static RecipeBox box;
    private File userRecipeFile;

    public void init() throws IOException {
        createDatabaseFile();
        box = this;
    }

    public static RecipeBox getInstance() {
        return box;
    }

    public RecipeBox(){
        recipes=new ArrayList<Recipe>();
    }

    public ArrayList<Recipe> getRecipes(){
        return recipes;
    }

    public void addRecipe(Recipe newRecipe){
        recipes.add(newRecipe);
    }

    public void createDatabaseFile() throws IOException {
        userRecipeFile = new File(CSV_FILENAME);
        if (!userRecipeFile.exists()) {
            userRecipeFile.createNewFile();
        }
    }

    public void storeRecipes(){
        try {
            FileWriter fw= new FileWriter(userRecipeFile, true);
            CSVWriter csvw = new CSVWriter(fw);
            for(Recipe r: recipes){
                String name=r.getName();
                String portions=String.valueOf(r.getPortions());




            }
            HashSet<String> rset= new HashSet<String>();
            String[] recipeInfo ={};
            csvw.writeNext(recipeInfo);
            csvw.flush();
            csvw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
