package pl.whatToEat.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Recipe;
import pl.whatToEat.View.RecipeView;

import java.io.IOException;

public class InstructionsController {

    private final RecipeController recipeController;

    public InstructionsController( RecipeController recipeController) {
        this.recipeController = recipeController;
    }
    public void run(Screen screen, Recipe recipe) {
        try {
            RecipeView.printInstructions(screen, recipe.getInstructions());
            KeyStroke keyStroke = screen.readInput();
            while(keyStroke.getKeyType() != KeyType.Escape) {
                keyStroke = screen.readInput();
            }
            recipeController.run(screen, recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
