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
            textGraphics.setForegroundColor(new TextColor.RGB(153,255,1));
            textGraphics.putString(0, 0," _____      _            __   __                _____                         _ _            _       \n", SGR.BOLD);
            textGraphics.putString(0, 1,"|  ___|    | |           \\ \\ / /               |_   _|                       | (_)          | |      \n", SGR.BOLD);
            textGraphics.putString(0, 2,"| |__ _ __ | |_ ___ _ __  \\ V /___  _   _ _ __   | | _ __   __ _ _ __ ___  __| |_  ___ _ __ | |_ ___ \n", SGR.BOLD);
            textGraphics.putString(0, 3,"|  __| '_ \\| __/ _ \\ '__|  \\ // _ \\| | | | '__|  | || '_ \\ / _` | '__/ _ \\/ _` | |/ _ \\ '_ \\| __/ __|\n", SGR.BOLD);
            textGraphics.putString(0, 4,"| |__| | | | ||  __/ |     | | (_) | |_| | |    _| || | | | (_| | | |  __/ (_| | |  __/ | | | |_\\__ \\\n", SGR.BOLD);
            textGraphics.putString(0, 5,"\\____/_| |_|\\__\\___|_|     \\_/\\___/ \\__,_|_|    \\___/_| |_|\\__, |_|  \\___|\\__,_|_|\\___|_| |_|\\__|___/\n", SGR.BOLD);
            textGraphics.putString(0, 6,"                                                              __/ |                                    \n", SGR.BOLD);
            textGraphics.putString(0, 7,"                                                             |___/                                     ", SGR.BOLD);

            textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
            if(ingredientList != null && !ingredientList.isEmpty()) {
                int i=5, j=10;
                textGraphics.putString(3, 8, "Your Ingredients:", SGR.BOLD);
                for (String s : ingredientList) {
                    if(j < 25) {
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
            }
            textGraphics.putString(78, 25, "<Press Escape to Exit>");
            textGraphics.drawLine(0, 26, 101, 26, new TextCharacter('_', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK, SGR.BOLD));
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
                    textGraphics.putString(29, 28, "Delete an Ingredient");
                    textGraphics.putString(58, 28, "Calculate and Show Recipes");
                    textGraphics.putString(90, 28, "Settings");
                }
                case DELETE_INGREDIENT -> {
                    textGraphics.putString(3, 28, "Add an Ingredient");
                    textGraphics.putString(29, 28, "Delete an Ingredient", SGR.BOLD, SGR.BLINK);
                    textGraphics.putString(58, 28, "Calculate and Show Recipes");
                    textGraphics.putString(90, 28, "Settings");
                }
                case SHOW_RECIPES -> {
                    textGraphics.putString(3, 28, "Add an Ingredient");
                    textGraphics.putString(29, 28, "Delete an Ingredient");
                    textGraphics.putString(58, 28, "Calculate and Show Recipes", SGR.BOLD, SGR.BLINK);
                    textGraphics.putString(90, 28, "Settings");
                }
                case SETTINGS -> {
                    textGraphics.putString(3, 28, "Add an Ingredient");
                    textGraphics.putString(29, 28, "Delete an Ingredient");
                    textGraphics.putString(58, 28, "Calculate and Show Recipes");
                    textGraphics.putString(90, 28, "Settings", SGR.BOLD, SGR.BLINK);
                }
            }
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
