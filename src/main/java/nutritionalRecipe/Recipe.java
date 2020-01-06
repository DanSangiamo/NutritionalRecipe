package nutritionalRecipe;
import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private int numPortions;

    public Recipe(String name){
        ingredients=new ArrayList<Ingredient>();
        int numPortions;
        this.name=name;
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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient newIngredient){
        ingredients.add(newIngredient);
    }

    public int findCalories(){
        int totalCalories=0;
        for(int i=0; i<ingredients.size(); i++) {
        totalCalories += ingredients.get(i).getCalories();
         }
        return totalCalories;
    }

    public int findFat(){
        int totalGramsFat=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalGramsFat+= ingredients.get(i).getFat();
        }
        return totalGramsFat;
    }

    public int findProtein(){
        int totalProtein=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalProtein+= ingredients.get(i).getProtein();
        }
        return totalProtein;
    }

    public int findCarbs(){
        int totalCarbs=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalCarbs+= ingredients.get(i).getCarbs();
        }
        return totalCarbs;
    }

    public int findSugar(){
        int totalSugar=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalSugar+= ingredients.get(i).getSugar();
        }
        return totalSugar;
    }

    public int findFiber(){
        int totalFiber=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalFiber+= ingredients.get(i).getFiber();
        }
        return totalFiber;
    }













}
