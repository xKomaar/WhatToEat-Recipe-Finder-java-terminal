package pl.whatToEat.Controller;

import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Recipe;
import pl.whatToEat.Model.RecipeCalculator;
import pl.whatToEat.Model.Selectors;
import pl.whatToEat.View.CookingAnimationView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class CalculateRecipesController {

    private ArrayList<Recipe> recipeList;
    private RecipeCalculator recipeCalculator;

    public CalculateRecipesController() {
        recipeCalculator = new RecipeCalculator(50);
    }

    public void run(Screen screen, ArrayList<String> ingredientList) {

        recipeList = recipeCalculator.calculateRecipes(ingredientList);
        CookingAnimationView.printPot(screen);
        try {
            for(int i=0; i < 4; i++) {
                CookingAnimationView.printSpoon(screen, Selectors.SpoonPosition.RIGHT);
                Thread.sleep(250);
                CookingAnimationView.printSpoon(screen, Selectors.SpoonPosition.MIDDLE);
                Thread.sleep(250);
                CookingAnimationView.printSpoon(screen, Selectors.SpoonPosition.LEFT);
                Thread.sleep(250);
                CookingAnimationView.printSpoon(screen, Selectors.SpoonPosition.MIDDLE);
                Thread.sleep(250);
            }

            if(!recipeList.isEmpty()) {
                Recipe recipe = recipeList.get(0);
                System.out.println(recipe.getMatchPercent());
                System.out.println(recipe.getTitle());
                System.out.println(recipe.getInstructions());
                recipe.getIngredientList().forEach(System.out::println);

                recipe = recipeList.get(1);
                System.out.println(recipe.getMatchPercent());
                System.out.println(recipe.getTitle());
                System.out.println(recipe.getInstructions());
                recipe.getIngredientList().forEach(System.out::println);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
