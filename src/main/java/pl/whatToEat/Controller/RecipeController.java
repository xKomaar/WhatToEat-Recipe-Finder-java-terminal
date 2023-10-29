package pl.whatToEat.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Recipe;
import pl.whatToEat.View.RecipeView;

import java.io.IOException;

public class RecipeController {
    private final InstructionsController instructionsController;

    public RecipeController() {
        instructionsController = new InstructionsController(this);
    }

    public void run(Screen screen, Recipe recipe) {
        try {

            RecipeView.printRecipe(screen, recipe);
            KeyStroke keyStroke = screen.readInput();
            while(keyStroke.getKeyType() != KeyType.Escape && keyStroke.getKeyType() != KeyType.Enter && keyStroke.getKeyType() != KeyType.EOF) {
                keyStroke = screen.readInput();
            }
            if(keyStroke.getKeyType() == KeyType.Enter) {
                instructionsController.run(screen, recipe);
            }
            if(keyStroke.getKeyType() == KeyType.EOF) {
                screen.close();
                System.exit(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
