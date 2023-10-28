package pl.whatToEat.Controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pl.whatToEat.View.StartPageView;

import java.io.IOException;


public class MainController {
    private IngredientsInputController ingredientsInputController;
    private Screen screen;

    public void run() {
        ingredientsInputController = new IngredientsInputController(this);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;
        try {
            terminal = terminalFactory.setTerminalEmulatorTitle("What to Eat: Recipe Finder")
                    .setInitialTerminalSize(new TerminalSize(101,30))
                    .createTerminal();

            screen = new TerminalScreen(terminal);
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
    public  void reset() {
        try {
            ingredientsInputController.run(screen);
        } catch (InterruptedException e) {
            e.printStackTrace();;
        }
    }
}