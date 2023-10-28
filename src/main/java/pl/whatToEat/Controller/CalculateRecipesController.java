package pl.whatToEat.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Ingredient;
import pl.whatToEat.Model.Recipe;
import pl.whatToEat.Model.RecipeCalculator;
import pl.whatToEat.Model.Selectors;
import pl.whatToEat.View.CookingAnimationView;
import pl.whatToEat.View.RecipeListView;
import pl.whatToEat.View.RecipeView;

import java.io.IOException;
import java.util.ArrayList;

public class CalculateRecipesController {

    private final RecipeCalculator recipeCalculator;
    private final RecipeController recipeController;
    private final MainController mainController;

    public CalculateRecipesController(MainController mainController) {
        recipeCalculator = new RecipeCalculator();
        recipeController = new RecipeController();
        this.mainController = mainController;
    }

    public void run(Screen screen, ArrayList<String> ingredientList) {

        ArrayList<Recipe> recipeList;
        recipeList = recipeCalculator.calculateRecipes(ingredientList);
        CookingAnimationView.printPot(screen);
        try {
            for(int i=0; i < 4; i++) {
                CookingAnimationView.printSpoon(screen, Selectors.SpoonPosition.RIGHT);
                Thread.sleep(200);
                CookingAnimationView.printSpoon(screen, Selectors.SpoonPosition.MIDDLE);
                Thread.sleep(200);
                CookingAnimationView.printSpoon(screen, Selectors.SpoonPosition.LEFT);
                Thread.sleep(200);
                CookingAnimationView.printSpoon(screen, Selectors.SpoonPosition.MIDDLE);
                Thread.sleep(200);
            }
            int startIndex = 0, selectedIndex = 0, pageNumber = 1;
            RecipeListView.printList(screen, recipeList, startIndex, selectedIndex, pageNumber);

            KeyStroke keyStroke = screen.readInput();
            while(keyStroke.getKeyType() != KeyType.Escape) {

                if (keyStroke.getKeyType() == KeyType.Enter) {
                    if(!recipeList.isEmpty()) {
                        recipeController.run(screen,recipeList.get(selectedIndex));
                    }
                    RecipeListView.printList(screen, recipeList, startIndex, selectedIndex, pageNumber);
                }
                if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                    selectedIndex++;
                    if(selectedIndex > startIndex+7 || selectedIndex == recipeList.size()) {
                        selectedIndex = startIndex;
                    }
                    RecipeListView.printList(screen, recipeList, startIndex, selectedIndex, pageNumber);
                }
                if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                    selectedIndex--;
                    if (selectedIndex < startIndex) {
                        selectedIndex = startIndex + 7;
                        if(selectedIndex >= recipeList.size()) {
                            selectedIndex = recipeList.size()-1;
                        }
                    }
                    RecipeListView.printList(screen, recipeList, startIndex, selectedIndex, pageNumber);
                }
                if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                    if(recipeList.size() > 8) {
                        startIndex += 8;
                        pageNumber++;
                        if(startIndex >= recipeList.size()) {
                            startIndex = 0;
                            pageNumber = 1;
                        }
                        selectedIndex = startIndex;
                    }
                    RecipeListView.printList(screen, recipeList, startIndex, selectedIndex, pageNumber);
                }
                if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                    if(recipeList.size() > 8) {
                        startIndex -= 8;
                        pageNumber--;
                        if(startIndex < 0) {
                            startIndex = recipeList.size() - recipeList.size()%8;
                            pageNumber = recipeList.size()/8;
                            if(recipeList.size()%8 != 0) {
                                pageNumber++;
                            }
                        }
                        selectedIndex = startIndex;
                    }
                    RecipeListView.printList(screen, recipeList, startIndex, selectedIndex, pageNumber);
                }
                keyStroke = screen.readInput();
            }

            //reset all ingredients availability
            for(Recipe r : recipeList) {
                for(Ingredient i : r.getIngredientList()) {
                    i.setAvailable(false);
                }
            }
            mainController.reset();

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }
}
