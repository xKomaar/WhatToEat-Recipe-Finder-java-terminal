package pl.whatToEat.Controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pl.whatToEat.Model.RecipeJsonParser;
import pl.whatToEat.Model.RecipeModel;
import pl.whatToEat.View.IngredientsInputView;
import pl.whatToEat.View.StartPageView;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    public static void main(String[] args) {
        IngredientsInputController ingredientsInputController = new IngredientsInputController();

        ArrayList<RecipeModel> recipeList = RecipeJsonParser.getRecipeList();

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;

        try {
            terminal = terminalFactory.setInitialTerminalSize(new TerminalSize(100,30)).createTerminal();

            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();

            StartPageView.printStartPage(screen);
            IngredientsInputView.printIngredientsInputView(screen, null);
            ingredientsInputController.run(screen);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (terminal != null) {
                try {
                    terminal.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run() {

    }
}