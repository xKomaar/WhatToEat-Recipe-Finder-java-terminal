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
import java.util.regex.Pattern;

public class IngredientsInputController {
    private ArrayList<String> ingredientList;

    private final DeleteIngredientController deleteIngredientController;
    private final CalculateRecipesController calculateRecipesController;
    private final SettingsController settingsController;

    public IngredientsInputController(MainController mainController) {
        deleteIngredientController = new DeleteIngredientController();
        calculateRecipesController = new CalculateRecipesController(mainController);
        settingsController = new SettingsController();
        ingredientList = new ArrayList<>();
    }

    public void run(Screen screen) throws InterruptedException {
        Selectors.InputIngredientsSelectors selector = Selectors.InputIngredientsSelectors.ADD_INGREDIENT;

        while(true) {
            IngredientsInputView.printIngredientsInputView(screen, ingredientList);
            IngredientsInputView.printMenu(screen, selector);
            try {
                KeyStroke keyStroke = screen.readInput();
                while(keyStroke.getKeyType() != KeyType.Enter && keyStroke.getKeyType() != KeyType.Escape) {
                    if(keyStroke.getKeyType() == KeyType.ArrowRight) {
                        if(selector == Selectors.InputIngredientsSelectors.ADD_INGREDIENT) {
                            selector = Selectors.InputIngredientsSelectors.DELETE_INGREDIENT;
                        }
                        else if(selector == Selectors.InputIngredientsSelectors.DELETE_INGREDIENT) {
                            selector = Selectors.InputIngredientsSelectors.SHOW_RECIPES;
                        }
                        else if(selector == Selectors.InputIngredientsSelectors.SHOW_RECIPES) {
                            selector = Selectors.InputIngredientsSelectors.SETTINGS;
                        }
                        else {
                            selector = Selectors.InputIngredientsSelectors.ADD_INGREDIENT;
                        }
                        IngredientsInputView.printMenu(screen, selector);
                    }
                    if(keyStroke.getKeyType() == KeyType.ArrowLeft) {
                        if(selector == Selectors.InputIngredientsSelectors.ADD_INGREDIENT) {
                            selector = Selectors.InputIngredientsSelectors.SETTINGS;
                        }
                        else if(selector == Selectors.InputIngredientsSelectors.SETTINGS) {
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
                if(keyStroke.getKeyType() == KeyType.Escape) {
                    return;
                }
                switch (selector) {
                    case ADD_INGREDIENT -> this.addIngredient(screen);
                    case DELETE_INGREDIENT -> {
                        if(ingredientList != null && !ingredientList.isEmpty()) {
                            deleteIngredientController.deleteIngredient(screen, ingredientList);
                        }
                    }
                    case SHOW_RECIPES -> {
                        if(!ingredientList.isEmpty()) {
                            calculateRecipesController.run(screen, ingredientList);
                            return;
                        }
                    }
                    case SETTINGS -> settingsController.run(screen);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addIngredient(Screen screen) {
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        String input = new TextInputDialogBuilder()
                .setTitle("Enter an Ingredient (Single Form)")
                .setTextBoxSize(new TerminalSize(1,1))
                .build()
                .showDialog(textGUI);

        if(input != null) {
            input = input.toLowerCase();
            if(!input.equals("") && !ingredientList.contains(input) && isAWord(input)) {
                ingredientList.add(input);
            }
        }
    }

    private boolean isAWord(String s) {
        Pattern pattern = Pattern.compile("[a-z\\s]+");
        if(pattern.matcher(s).matches()) {
            return true;
        }
        return false;
    }
}
