package pl.whatToEat.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteIngredientView {
    public static void printDeleteView(Screen screen) throws RuntimeException, InterruptedException {
        try {
            screen.clear();

            final TextGraphics textGraphics = screen.newTextGraphics();

            textGraphics.putString(18, 0, " __    _  _  __ __    _       ___    __ _  __ _ ___ __   ___\n", SGR.BOLD);
            textGraphics.putString(18, 1, "/  |_|/ \\/ \\(_ |_    |_||\\|    | |\\|/__|_)|_ | \\ | |_ |\\| | \n", SGR.BOLD);
            textGraphics.putString(18, 2, "\\__| |\\_/\\_/__)|__   | || |   _|_| |\\_|| \\|__|_/_|_|__| | | ", SGR.BOLD);

            textGraphics.putString(5, 28, "<Press Enter to Delete>", SGR.BOLD);
            textGraphics.putString(70, 28, "<Press Escape to Cancel>", SGR.BOLD);

            textGraphics.drawLine(0, 26, 100, 26, new TextCharacter('_', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK, SGR.BOLD));
            screen.refresh();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printIngredients(Screen screen, ArrayList<String> ingredientList, int index) throws RuntimeException, InterruptedException {
        try {
            final TextGraphics textGraphics = screen.newTextGraphics();

            int i=18, j=5;
            for (String s : ingredientList) {

                if (ingredientList.indexOf(s) == index) {
                    textGraphics.putString(i, j, s + "     ", SGR.BOLD);
                }
                else {
                    textGraphics.putString(i, j, s + "     ");
                }

                i += s.length() + 5;
                if(i > 70) {
                    i=18;
                    j += 2;
                }
            }

            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
