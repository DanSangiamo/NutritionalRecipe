package nutritionalRecipe;

/**
 * Creates an object Ingredient
 */
public class IngredientsAsObjects {
    private String ingredientName;
    private String unitOfMeasure;
    private double ingredientQuantity;

    /**
     *
     * @param name ingredient name
     * @param uom unit of measure
     * @param quantity ingredient quantity
     */
    public IngredientsAsObjects (String name, String uom, double quantity) {
        ingredientName = name;
        unitOfMeasure = uom;
        ingredientQuantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(double ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }





}
