package pl.whatToEat.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteIngredientView {
    public static void printDeleteView(Screen screen) throws RuntimeException {
        try {
            screen.clear();

            final TextGraphics textGraphics = screen.newTextGraphics();

            textGraphics.putString(1, 0, " _____ _                                          _____                         _ _            _   \n", SGR.BOLD);
            textGraphics.putString(1, 1, "/  __ \\ |                                        |_   _|                       | (_)          | |  \n", SGR.BOLD);
            textGraphics.putString(1, 2, "| /  \\/ |__   ___   ___  ___  ___    __ _ _ __     | | _ __   __ _ _ __ ___  __| |_  ___ _ __ | |_ \n", SGR.BOLD);
            textGraphics.putString(1, 3, "| |   | '_ \\ / _ \\ / _ \\/ __|/ _ \\  / _` | '_ \\    | || '_ \\ / _` | '__/ _ \\/ _` | |/ _ \\ '_ \\| __|\n", SGR.BOLD);
            textGraphics.putString(1, 4, "| \\__/\\ | | | (_) | (_) \\__ \\  __/ | (_| | | | |  _| || | | | (_| | | |  __/ (_| | |  __/ | | | |_ \n", SGR.BOLD);
            textGraphics.putString(1, 5, " \\____/_| |_|\\___/ \\___/|___/\\___|  \\__,_|_| |_|  \\___/_| |_|\\__, |_|  \\___|\\__,_|_|\\___|_| |_|\\__|\n", SGR.BOLD);
            textGraphics.putString(1, 6, "                                                              __/ |                                \n", SGR.BOLD);
            textGraphics.putString(1, 7, "                                                             |___/                                 ", SGR.BOLD);

            textGraphics.putString(5, 28, "<Press Enter to Delete>", SGR.BOLD);
            textGraphics.putString(70, 28, "<Press Escape to Cancel>", SGR.BOLD);

            textGraphics.drawLine(0, 26, 101, 26, new TextCharacter('_', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK, SGR.BOLD));
            screen.refresh();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printIngredients(Screen screen, ArrayList<String> ingredientList, int index) throws RuntimeException, InterruptedException {
        try {
            final TextGraphics textGraphics = screen.newTextGraphics();

            int i=3, j=9;
            for (String s : ingredientList) {

                if (ingredientList.indexOf(s) == index) {
                    textGraphics.setForegroundColor(new TextColor.RGB(255, 51, 51));
                    textGraphics.putString(i, j, s + "     ", SGR.BOLD, SGR.BLINK);
                }
                else {
                    textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
                    textGraphics.putString(i, j, s + "     ");
                }

                i += s.length() + 5;
                if(i > 85) {
                    i=3;
                    j += 2;
                }
            }

            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
