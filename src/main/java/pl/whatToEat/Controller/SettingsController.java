package pl.whatToEat.Controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Selectors;
import pl.whatToEat.View.SettingsView;

import java.io.IOException;
import java.util.regex.Pattern;

public class SettingsController {
    public void run(Screen screen) {
        Selectors.ChosenSetting selector = Selectors.ChosenSetting.THRESHOLD;
        SettingsView.printSettingsView(screen);
        SettingsView.printSettingsList(screen, selector);
        try {
            KeyStroke keyStroke = screen.readInput();
            while (keyStroke.getKeyType() != KeyType.Escape) {
                if(keyStroke.getKeyType() == KeyType.ArrowUp) {
                    if(selector == Selectors.ChosenSetting.SORT) {
                        selector = Selectors.ChosenSetting.THRESHOLD;
                    }
                    else {
                        selector = Selectors.ChosenSetting.SORT;
                    }
                    SettingsView.printSettingsList(screen, selector);
                }
                if(keyStroke.getKeyType() == KeyType.ArrowDown) {
                    if(selector == Selectors.ChosenSetting.THRESHOLD) {
                        selector = Selectors.ChosenSetting.SORT;
                    }
                    else {
                        selector = Selectors.ChosenSetting.THRESHOLD;
                    }
                    SettingsView.printSettingsList(screen, selector);
                }
                if(keyStroke.getKeyType() == KeyType.Enter) {
                    if(selector == Selectors.ChosenSetting.SORT) {
                        if(Selectors.sortByIngredientCountFirst) {
                            Selectors.sortByIngredientCountFirst = false;
                        }
                        else {
                            Selectors.sortByIngredientCountFirst = true;
                        }
                    }
                    else {
                        changeThreshold(screen);
                    }
                    SettingsView.printSettingsView(screen);
                    SettingsView.printSettingsList(screen, selector);
                }
                keyStroke = screen.readInput();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void changeThreshold(Screen screen) {
        Pattern pattern = Pattern.compile("[0-9]+");
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        String input = new TextInputDialogBuilder()
                .setTitle("Enter Match Threshold")
                .setTextBoxSize(new TerminalSize(1,1))
                .build()
                .showDialog(textGUI);

        if(input != null) {
            if(!input.equals("")) {
                if(pattern.matcher(input).matches()) {
                    int newThreshold = Integer.parseInt(input);
                    if(newThreshold > 100) {
                        Selectors.matchThreshold = 100;
                    }
                    else if(newThreshold < 0) {
                        Selectors.matchThreshold = 100;
                    }
                    else {
                        Selectors.matchThreshold = newThreshold;
                    }
                }
            }
        }
    }
}
