package pl.whatToEat.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.View.DeleteIngredientView;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteIngredientController {
    public void deleteIngredient(Screen screen, ArrayList<String> ingredientList) throws InterruptedException {
        try {
            int index = 0;
            DeleteIngredientView.printDeleteView(screen);
            DeleteIngredientView.printIngredients(screen, ingredientList, index);

            KeyStroke keyStroke = screen.readInput();
            while(keyStroke.getKeyType() != KeyType.Enter && keyStroke.getKeyType() != KeyType.Escape) {
                if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                    index++;
                    if(index == ingredientList.size()) {
                        index = 0;
                    }
                    DeleteIngredientView.printIngredients(screen, ingredientList, index);
                }
                if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                    index--;
                    if(index == -1) {
                        index = ingredientList.size()-1;
                    }
                    DeleteIngredientView.printIngredients(screen, ingredientList, index);
                }
                keyStroke = screen.readInput();
            }
            if(keyStroke.getKeyType() == KeyType.Enter) {
                ingredientList.remove(index);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
