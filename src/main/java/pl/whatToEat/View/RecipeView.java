package pl.whatToEat.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Ingredient;
import pl.whatToEat.Model.Recipe;

import java.io.IOException;

public class RecipeView {

    public static void printRecipe(Screen screen, Recipe recipe) {
        try {
            screen.clear();

            final TextGraphics textGraphics = screen.newTextGraphics();
            textGraphics.setForegroundColor(new TextColor.RGB(0,153,153));
            textGraphics.putString(23, 0, "__   __                ______          _            \n", SGR.BOLD);
            textGraphics.putString(23, 1, "\\ \\ / /                | ___ \\        (_)           \n", SGR.BOLD);
            textGraphics.putString(23, 2, " \\ V /___  _   _ _ __  | |_/ /___  ___ _ _ __   ___ \n", SGR.BOLD);
            textGraphics.putString(23, 3, "  \\ // _ \\| | | | '__| |    // _ \\/ __| | '_ \\ / _ \\\n", SGR.BOLD);
            textGraphics.putString(23, 4, "  | | (_) | |_| | |    | |\\ \\  __/ (__| | |_) |  __/\n", SGR.BOLD);
            textGraphics.putString(23, 5, "  \\_/\\___/ \\__,_|_|    \\_| \\_\\___|\\___|_| .__/ \\___|\n", SGR.BOLD);
            textGraphics.putString(23, 6, "                                        | |         \n", SGR.BOLD);
            textGraphics.putString(23, 7, "                                        |_|         ", SGR.BOLD);

            textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
            textGraphics.putString(5, 28, "<Press Enter to See Whole Instructions>", SGR.BOLD);
            textGraphics.putString(70, 28, "<Press Escape to Go Back>", SGR.BOLD);
            textGraphics.drawLine(0, 26, 101, 26, new TextCharacter('_', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK, SGR.BOLD));

            textGraphics.putString(1, 8, "Recipe Name: ", SGR.BOLD);
            textGraphics.putString(14, 8, recipe.getTitle());

            textGraphics.putString(1, 10, "Ingredient List: ", SGR.BOLD);
            int pos1=1, pos2=11;
            for(Ingredient ingredient : recipe.getIngredientList()) {
                if(ingredient.getName().length() < 100) {
                    if(ingredient.isAvailable()) {
                        textGraphics.setForegroundColor(new TextColor.RGB(102,255,178));
                    } else {
                        textGraphics.setForegroundColor(new TextColor.RGB(255,102,102));
                    }
                    if(pos1 > 80 || pos1+ingredient.getName().length() > 95) {
                        pos1=1;
                        pos2++;
                    }
                    if(ingredient.equals(recipe.getIngredientList().get(recipe.getIngredientList().size()-1))) {
                        textGraphics.putString(pos1, pos2, ingredient.getName());
                    }
                    else {
                        textGraphics.putString(pos1, pos2, ingredient.getName()+"; ");
                        pos1+=2;
                    }

                    pos1 += ingredient.getName().length();
                }
            }
            textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
            textGraphics.putString(1, 17, "Instuctions: ", SGR.BOLD);
            pos2 = 18;
            String str = "";
            for(int i = 0; i<recipe.getInstructions().length(); i++) {
                if((str.length() > 80 && (recipe.getInstructions().charAt(i) == ',' || recipe.getInstructions().charAt(i) == '.'
                        || recipe.getInstructions().charAt(i) == ';' || recipe.getInstructions().charAt(i) == ' '))
                        || i==recipe.getInstructions().length()-1) {
                    str += recipe.getInstructions().charAt(i);
                    textGraphics.putString(1, pos2, str);
                    pos2++;
                    if(pos2 == 25) {
                        str += "...";
                        textGraphics.putString(1, 24, str);
                        break;
                    }
                    str = "";
                } else {
                    str += recipe.getInstructions().charAt(i);
                }
            }
            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printInstructions(Screen screen, String instructions) {
        try {
            screen.clear();

            final TextGraphics textGraphics = screen.newTextGraphics();
            textGraphics.putString(37, 28, "<Press Escape to Go Back>", SGR.BOLD);
            textGraphics.drawLine(0, 26, 101, 26, new TextCharacter('_', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK, SGR.BOLD));
            int pos = 1;
            String str = "";
            for(int i = 0; i<instructions.length(); i++) {
                if((str.length() > 80 && (instructions.charAt(i) == ',' || instructions.charAt(i) == '.'
                        || instructions.charAt(i) == ';' || instructions.charAt(i) == ' '))
                        || i==instructions.length()-1) {
                    str += instructions.charAt(i);
                    textGraphics.putString(1, pos, str);
                    pos++;
                    if(pos == 25) {
                        str += "...";
                        textGraphics.putString(1, 24, str);
                        break;
                    }
                    str = "";
                } else {
                    str += instructions.charAt(i);
                }
            }
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
