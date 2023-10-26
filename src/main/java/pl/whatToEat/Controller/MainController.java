package pl.whatToEat.Controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pl.whatToEat.View.StartPageView;

import java.io.IOException;


public class MainController {

    public static void main(String[] args) {
        IngredientsInputController ingredientsInputController = new IngredientsInputController();

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;

        try {
            terminal = terminalFactory.setInitialTerminalSize(new TerminalSize(101,30)).createTerminal();

            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();

            StartPageView.printStartPage(screen);

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
}