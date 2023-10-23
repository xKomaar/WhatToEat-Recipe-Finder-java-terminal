package pl.whatToEat.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

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
            textGraphics.putString(1, 3,"                                                                                                   ", SGR.BOLD);

            textGraphics.putString(3, 5, "Your Ingredients:", SGR.BOLD);

            if(ingredientList != null) {
                int length = 0, pos = 6;
                String str = "";
                for(String s : ingredientList) {
                    str += (s + ", ");
                    length += s.length();
                    if(length > 60) {
                        textGraphics.putString(5, pos++, str, SGR.ITALIC);
                        length = 0;
                        str = "";
                    }
                }
                textGraphics.putString(5, pos, str, SGR.ITALIC);
            }

            screen.refresh();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
