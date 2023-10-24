package pl.whatToEat.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public class RecipeCalculator {

    ArrayList<Recipe> recipeList;

    private final int threshold;

    public RecipeCalculator(int threshold) {

        recipeList = RecipeJsonParser.getRecipeList();
        this.threshold = threshold;
    }

    public TreeMap<Integer, Recipe> calculateRecipes(ArrayList<String> ingredientsList) {
        TreeMap<Integer, Recipe> recipeMap = new TreeMap<>(Comparator.reverseOrder());

        for(Recipe recipe : recipeList) {
            int validIngredientCount = 0;
            int recipeIngredientCount = recipe.ingredientList().size();
            for(String userIngredient : ingredientsList) {
                for(String recipeIngredient : recipe.ingredientList()) {
                    if (recipeIngredient.contains(userIngredient)) {
                        validIngredientCount++;
                    }
                }
            }
            Integer matchPercent = (Integer)(validIngredientCount/recipeIngredientCount*100);
            if(matchPercent > threshold) {
                recipeMap.put(matchPercent, recipe);
            }
        }
        return recipeMap;
    }
}
