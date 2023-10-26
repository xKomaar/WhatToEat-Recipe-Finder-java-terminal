package pl.whatToEat.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Recipe;

import java.io.IOException;
import java.util.ArrayList;

public class RecipeListView {
    public static void printRecipeListView(Screen screen, int pageNumber) {
        try {
            screen.clear();

            final TextGraphics textGraphics = screen.newTextGraphics();
            textGraphics.setForegroundColor(new TextColor.RGB(255, 182, 1));
            textGraphics.putString(11, 0,"  ___             _ _       _     _       ______          _                 \n", SGR.BOLD);
            textGraphics.putString(11, 1," / _ \\           (_) |     | |   | |      | ___ \\        (_)                \n", SGR.BOLD);
            textGraphics.putString(11, 2,"/ /_\\ \\_   ____ _ _| | __ _| |__ | | ___  | |_/ /___  ___ _ _ __   ___  ___ \n", SGR.BOLD);
            textGraphics.putString(11, 3,"|  _  \\ \\ / / _` | | |/ _` | '_ \\| |/ _ \\ |    // _ \\/ __| | '_ \\ / _ \\/ __|\n", SGR.BOLD);
            textGraphics.putString(11, 4,"| | | |\\ V / (_| | | | (_| | |_) | |  __/ | |\\ \\  __/ (__| | |_) |  __/\\__ \\\n", SGR.BOLD);
            textGraphics.putString(11, 5,"\\_| |_/ \\_/ \\__,_|_|_|\\__,_|_.__/|_|\\___| \\_| \\_\\___|\\___|_| .__/ \\___||___/\n", SGR.BOLD);
            textGraphics.putString(11, 6,"                                                           | |              \n", SGR.BOLD);
            textGraphics.putString(11, 7,"                                                           |_|              ", SGR.BOLD);

            textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
            textGraphics.putString(5, 26, "<Left Arrow - Previous Page>", SGR.BOLD);
            textGraphics.putString(5, 28, "<Arrow Up/Down - Select Recipes>", SGR.BOLD);
            textGraphics.putString(33, 27, "<Escape - Go Back to Ingredient Input>", SGR.BOLD);
            textGraphics.putString(70, 28, "<Enter - Read a Recipe>", SGR.BOLD);
            textGraphics.putString(70, 26, "<Right Arrow - Next Page>", SGR.BOLD);
            textGraphics.putString(90, 23, "Page " + pageNumber, SGR.BOLD);
            textGraphics.drawLine(0, 24, 101, 24, new TextCharacter('_', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK, SGR.BOLD));

            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printList(Screen screen, ArrayList<Recipe> recipeList, int startIndex, int selectedIndex, int pageNumber) {
        try {
            screen.clear();
            printRecipeListView(screen, pageNumber);
            final TextGraphics textGraphics = screen.newTextGraphics();
            if(recipeList.isEmpty()) {
                textGraphics.setForegroundColor(new TextColor.RGB(255, 51, 51));
                textGraphics.putString(19, 14, "Unfortunately, no recipes were found for given ingredients.", SGR.BOLD);
                textGraphics.putString(23, 15, "Press Escape to go back to Ingredient Input Menu.", SGR.BOLD);
                screen.refresh();
                return;
            }
            int row = 8;

            for(int i=startIndex; i<recipeList.size() && row <= 22; i++) {
                if(selectedIndex == i) {
                    textGraphics.setForegroundColor(new TextColor.RGB(51, 255, 255));
                    textGraphics.putString(3, row, recipeList.get(i).getTitle()+": ", SGR.BOLD, SGR.BLINK);
                    textGraphics.putString(5+recipeList.get(i).getTitle().length(), row, "Ingredient Match: " + recipeList.get(i).getMatchPercent() + "%, ", SGR.BOLD, SGR.BLINK);
                    textGraphics.putString(29+recipeList.get(i).getTitle().length(), row, "Ingredient Count: " + recipeList.get(i).getIngredientList().size(), SGR.BOLD, SGR.BLINK);
                }
                else {
                    textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
                    textGraphics.putString(3, row, recipeList.get(i).getTitle()+": ", SGR.BOLD);
                    if(recipeList.get(i).getMatchPercent() <= 100 &&recipeList.get(i).getMatchPercent() >= 85) {
                        textGraphics.setForegroundColor(new TextColor.RGB(154, 255, 0));
                    } else if (recipeList.get(i).getMatchPercent() < 85 &&recipeList.get(i).getMatchPercent() >= 70) {
                        textGraphics.setForegroundColor(new TextColor.RGB(255, 222, 0));
                    } else if (recipeList.get(i).getMatchPercent() < 70 &&recipeList.get(i).getMatchPercent() >= 60) {
                        textGraphics.setForegroundColor(new TextColor.RGB(255, 128, 0));
                    } else if (recipeList.get(i).getMatchPercent() < 60) {
                        textGraphics.setForegroundColor(new TextColor.RGB(255, 51, 51));
                    }
                    textGraphics.putString(5+recipeList.get(i).getTitle().length(), row, "Ingredient Match: " + recipeList.get(i).getMatchPercent() + "%, ");
                    textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
                    textGraphics.putString(29+recipeList.get(i).getTitle().length(), row, "Ingredient Count: " + recipeList.get(i).getIngredientList().size());
                }
                row += 2;
            }
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
