package nutritionalRecipe;
import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private int numPortions;
    private ArrayList<Integer> nutritionFacts;

    public Recipe(String name){
        ingredients=new ArrayList<Ingredient>();
        int numPortions;
        this.name=name;
        nutritionFacts=new ArrayList<Integer>();
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
        nutritionFacts.add(totalCalories);
        return totalCalories;
    }

    public int findFat(){
        int totalGramsFat=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalGramsFat+= ingredients.get(i).getFat();
        }
        nutritionFacts.add(totalGramsFat);
        return totalGramsFat;
    }

    public int findProtein(){
        int totalProtein=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalProtein+= ingredients.get(i).getProtein();
        }
        nutritionFacts.add(totalProtein);
        return totalProtein;
    }

    public int findCarbs(){
        int totalCarbs=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalCarbs+= ingredients.get(i).getCarbs();
        }
        nutritionFacts.add(totalCarbs);
        return totalCarbs;
    }

    public int findSugar(){
        int totalSugar=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalSugar+= ingredients.get(i).getSugar();
        }
        nutritionFacts.add(totalSugar);
        return totalSugar;
    }

    public int findFiber(){
        int totalFiber=0;
        for(int i=0; i<ingredients.size(); i++) {
            totalFiber+= ingredients.get(i).getFiber();
        }
        nutritionFacts.add(totalFiber);
        return totalFiber;
    }

    public String toStringIng(){
        String ingListStr=" ";
        for(int i=0; i<ingredients.size(); i++){
            ingListStr+=ingredients.get(i).toString();
        }
        return ingListStr;
    }

    public String recToString(String ingredientListString){
        return name +" "+ ingredientListString;
    }

    public String nfToString(){
        return "calories:" + findCalories() + ", "+ "fat:" + findFat() + "g, carbohydrates:"
                + findCarbs()+ "g, protein:" + findProtein() + "g, fiber:" + findFiber() + "g, sugar:" + findSugar() + "g";
    }
















}
