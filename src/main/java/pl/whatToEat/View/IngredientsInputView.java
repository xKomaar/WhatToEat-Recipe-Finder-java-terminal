package pl.whatToEat.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Selectors;

import java.io.IOException;
import java.util.ArrayList;

public class IngredientsInputView {

    public static void printIngredientsInputView(Screen screen, ArrayList<String> ingredientList) throws RuntimeException, InterruptedException {
        try {
            screen.clear();

            final TextGraphics textGraphics = screen.newTextGraphics();

            textGraphics.putString(1, 0,"____ _  _ ___ ____ ____    _   _ ____ _  _ ____    _ _  _ ____ ____ ____ ___  _ ____ _  _ ___ ____ \n", SGR.BOLD);
            textGraphics.putString(1, 1,"|___ |\\ |  |  |___ |__/     \\_/  |  | |  | |__/    | |\\ | | __ |__/ |___ |  \\ | |___ |\\ |  |  [__  \n", SGR.BOLD);
            textGraphics.putString(1, 2,"|___ | \\|  |  |___ |  \\      |   |__| |__| |  \\    | | \\| |__] |  \\ |___ |__/ | |___ | \\|  |  ___] \n", SGR.BOLD);

            if(ingredientList != null && !ingredientList.isEmpty()) {
                int i=5, j=7;
                textGraphics.putString(3, 5, "Your Ingredients:", SGR.BOLD);
                for (String s : ingredientList) {
                    textGraphics.putString(i, j, s);
                    i += s.length();
                    if (ingredientList.indexOf(s) != ingredientList.size()-1) {
                        textGraphics.putString(i, j, ",  ");
                    }
                    i +=  3;
                    if(i > 60) {
                        i=5;
                        j++;
                    }
                }
            }
            textGraphics.drawLine(0, 26, 100, 26, new TextCharacter('_', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK, SGR.BOLD));
            screen.refresh();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printMenu(Screen screen, Selectors.InputIngredientsSelectors selector) {

        try {
            final TextGraphics textGraphics = screen.newTextGraphics();
            switch (selector) {
                case ADD_INGREDIENT -> {
                    textGraphics.putString(3, 28, "Add an Ingredient", SGR.BOLD, SGR.BLINK);
                    textGraphics.putString(36, 28, "Delete an Ingredient" );
                    textGraphics.putString(70, 28, "Calculate and Show Recipes");
                }
                case DELETE_INGREDIENT -> {
                    textGraphics.putString(3, 28, "Add an Ingredient");
                    textGraphics.putString(36, 28, "Delete an Ingredient", SGR.BOLD, SGR.BLINK );
                    textGraphics.putString(70, 28, "Calculate and Show Recipes");
                }
                case SHOW_RECIPES -> {
                    textGraphics.putString(3, 28, "Add an Ingredient");
                    textGraphics.putString(36, 28, "Delete an Ingredient" );
                    textGraphics.putString(70, 28, "Calculate and Show Recipes", SGR.BOLD, SGR.BLINK);
                }
            }
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
