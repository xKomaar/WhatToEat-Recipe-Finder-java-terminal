package pl.whatToEat.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Selectors;

import java.io.IOException;

public class SettingsView {
    public static void printSettingsView(Screen screen) {
        try {
            screen.clear();

            final TextGraphics textGraphics = screen.newTextGraphics();
            textGraphics.setForegroundColor(new TextColor.RGB(128,128,128));
            textGraphics.putString(32, 0, " _____      _   _   _                 \n", SGR.BOLD);
            textGraphics.putString(32, 1, "/  ___|    | | | | (_)                \n", SGR.BOLD);
            textGraphics.putString(32, 2, "\\ `--.  ___| |_| |_ _ _ __   __ _ ___ \n", SGR.BOLD);
            textGraphics.putString(32, 3, " `--. \\/ _ \\ __| __| | '_ \\ / _` / __|\n", SGR.BOLD);
            textGraphics.putString(32, 4, "/\\__/ /  __/ |_| |_| | | | | (_| \\__ \\\n", SGR.BOLD);
            textGraphics.putString(32, 5, "\\____/ \\___|\\__|\\__|_|_| |_|\\__, |___/\n", SGR.BOLD);
            textGraphics.putString(32, 6, "                             __/ |    \n", SGR.BOLD);
            textGraphics.putString(32, 7, "                            |___/     ", SGR.BOLD);

            textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
            textGraphics.putString(5, 28, "<Arrow Up/Down - Select Settings>", SGR.BOLD);
            textGraphics.putString(45, 28, "<Enter - Change Setting>", SGR.BOLD);
            textGraphics.putString(77, 28, "<Escape - Go Back>", SGR.BOLD);
            textGraphics.drawLine(0, 26, 101, 26, new TextCharacter('_', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK, SGR.BOLD));

            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printSettingsList(Screen screen, Selectors.ChosenSetting chosenSetting) {
        try {
            final TextGraphics textGraphics = screen.newTextGraphics();
            switch (chosenSetting) {
                case THRESHOLD -> {
                    textGraphics.putString(5, 9, "Match Threshold: " + Selectors.matchThreshold + " (Default = 50)", SGR.BOLD);
                    if(Selectors.sortByIngredientCountFirst) {
                        textGraphics.putString(5, 11, "Sorting: First By Ingredient Count, then by Match Percent");
                    }
                    else {
                        textGraphics.putString(5, 11, "Sorting: First by Match Percent, then by Ingredient Count");
                    }
                }
                case SORT -> {
                    textGraphics.putString(5, 9, "Match Threshold: " + Selectors.matchThreshold + " (Default 50, Recommended 70+)");
                    if(Selectors.sortByIngredientCountFirst) {
                        textGraphics.putString(5, 11, "Sorting: First By Ingredient Count, then by Match Percent", SGR.BOLD);
                    }
                    else {
                        textGraphics.putString(5, 11, "Sorting: First by Match Percent, then by Ingredient Count", SGR.BOLD);
                    }
                }
            }
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
