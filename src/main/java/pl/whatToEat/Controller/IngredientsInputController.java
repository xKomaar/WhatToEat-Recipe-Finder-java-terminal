package pl.whatToEat.Controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.View.IngredientsInputView;

import java.io.IOException;
import java.util.ArrayList;

public class IngredientsInputController {
    private ArrayList<String> ingredientList;
    private IngredientsInputView ingredientsInputView = new IngredientsInputView();

    public IngredientsInputController() {
        ingredientList = new ArrayList<>();
    }

    public void run(Screen screen) throws InterruptedException {
        while(true) {
            IngredientsInputView.printIngredientsInputView(screen, ingredientList);
            try {
                KeyStroke keyStroke = screen.readInput();
                while(keyStroke.getKeyType() != KeyType.Enter) {

                }
                this.addIngredient(screen);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addIngredient(Screen screen) {
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        String input = new TextInputDialogBuilder()
                .setTitle("Enter an ingredient")
                .setTextBoxSize(new TerminalSize(1,1))
                .build()
                .showDialog(textGUI);

        ingredientList.add(input);
    }
}
