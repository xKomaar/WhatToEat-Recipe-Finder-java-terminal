package pl.whatToEat.Controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Selectors;
import pl.whatToEat.View.IngredientsInputView;

import java.io.IOException;
import java.util.ArrayList;

public class IngredientsInputController {
    private ArrayList<String> ingredientList;

    private DeleteIngredientController deleteIngredientController;

    public IngredientsInputController() {
        ingredientList = new ArrayList<>();
        deleteIngredientController = new DeleteIngredientController();
    }

    public void run(Screen screen) throws InterruptedException {
        Selectors.InputIngredientsSelectors selector = Selectors.InputIngredientsSelectors.ADD_INGREDIENT;

        while(true) {
            IngredientsInputView.printIngredientsInputView(screen, ingredientList);
            IngredientsInputView.printMenu(screen, selector);
            try {
                KeyStroke keyStroke = screen.readInput();
                while(keyStroke.getKeyType() != KeyType.Enter) {
                    if(keyStroke.getKeyType() == KeyType.ArrowRight) {
                        if(selector == Selectors.InputIngredientsSelectors.ADD_INGREDIENT) {
                            selector = Selectors.InputIngredientsSelectors.DELETE_INGREDIENT;
                        }
                        else if(selector == Selectors.InputIngredientsSelectors.DELETE_INGREDIENT) {
                            selector = Selectors.InputIngredientsSelectors.SHOW_RECIPES;
                        }
                        else {
                            selector = Selectors.InputIngredientsSelectors.ADD_INGREDIENT;
                        }
                        IngredientsInputView.printMenu(screen, selector);
                    }
                    if(keyStroke.getKeyType() == KeyType.ArrowLeft) {
                        if(selector == Selectors.InputIngredientsSelectors.ADD_INGREDIENT) {
                            selector = Selectors.InputIngredientsSelectors.SHOW_RECIPES;
                        }
                        else if(selector == Selectors.InputIngredientsSelectors.SHOW_RECIPES) {
                            selector = Selectors.InputIngredientsSelectors.DELETE_INGREDIENT;
                        }
                        else {
                            selector = Selectors.InputIngredientsSelectors.ADD_INGREDIENT;
                        }
                        IngredientsInputView.printMenu(screen, selector);
                    }
                    keyStroke = screen.readInput();
                }
                if(selector == Selectors.InputIngredientsSelectors.ADD_INGREDIENT) {
                    this.addIngredient(screen);
                }
                else if(selector == Selectors.InputIngredientsSelectors.DELETE_INGREDIENT) {
                    if(ingredientList != null && !ingredientList.isEmpty()) {
                        deleteIngredientController.deleteIngredient(screen, ingredientList);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addIngredient(Screen screen) {
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        String input = new TextInputDialogBuilder()
                .setTitle("Enter an ingredient")
                .setTextBoxSize(new TerminalSize(1,1))
                .build()
                .showDialog(textGUI);

        if(input != null && !input.equals("") && !ingredientList.contains(input)) {
            ingredientList.add(input);
        }
    }
}
