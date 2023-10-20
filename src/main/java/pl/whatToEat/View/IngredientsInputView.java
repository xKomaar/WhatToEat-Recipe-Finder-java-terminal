package pl.whatToEat.View;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;

public class IngredientsInputView {

    public static void printIngredientsInputView(Screen screen, ArrayList<String> ingredientList) throws RuntimeException, InterruptedException {
        try {
            System.out.println(ingredientList);
            screen.clear();

            final TextGraphics textGraphics = screen.newTextGraphics();

            textGraphics.putString(19, 0,"┏┓         ┓┏        ┳         ┓•      \n");
            textGraphics.putString(19, 1,"┣ ┏┓╋┏┓┏┓  ┗┫┏┓┓┏┏┓  ┃┏┓┏┓┏┓┏┓┏┫┓┏┓┏┓╋┏\n");
            textGraphics.putString(19, 2,"┗┛┛┗┗┗ ┛   ┗┛┗┛┗┻┛   ┻┛┗┗┫┛ ┗ ┗┻┗┗ ┛┗┗┛\n");
            textGraphics.putString(19, 3,"                         ┛             ");

            screen.refresh();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
