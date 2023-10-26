package pl.whatToEat.Controller;

import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Recipe;
import pl.whatToEat.Model.RecipeCalculator;
import pl.whatToEat.Model.Selectors;
import pl.whatToEat.View.CookingAnimationView;

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


            Recipe recipe = recipeMap.firstEntry().getValue();
            System.out.println(recipe.title());
            System.out.println(recipe.instructions());
            recipe.ingredientList().forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
