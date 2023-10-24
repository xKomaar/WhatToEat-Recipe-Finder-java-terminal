package pl.whatToEat.Controller;

import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Recipe;
import pl.whatToEat.Model.RecipeCalculator;

import java.util.ArrayList;
import java.util.TreeMap;

public class CalculateRecipesController {

    private TreeMap<Integer, Recipe> recipeMap;
    private RecipeCalculator recipeCalculator;

    public CalculateRecipesController() {
        recipeCalculator = new RecipeCalculator(50);
    }

    public void run(Screen screen, ArrayList<String> ingredientList) {
        recipeMap = recipeCalculator.calculateRecipes(ingredientList);

    }
}
