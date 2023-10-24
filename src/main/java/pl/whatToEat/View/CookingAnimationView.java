package pl.whatToEat.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pl.whatToEat.Model.Selectors;

import java.io.IOException;

public class CookingAnimationView {

    public static void printPot(Screen screen) {
        String title0 = "        |================================================|        \n";
        String title1 = "-*******==================================================*******-\n";
        String title2 = "-********==================================================********-\n";
        String title3 = "       |==================================================|       \n";
        String title4 = "       |==================================================|       \n";
        String title5 = "       |==================================================|           \n";
        String title6 = "       |==================================================|      \n";
        String title7 = "       |==================================================|           \n";
        String title8 = "        |================================================|        \n";
        String title9 = "         |==============================================|         \n";
        String title10 = "            |---==================================---|            \n";
        String title11 = "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
        String title12 = "@--------------@@@@ ";
        String title12_1 = "<Your Recipes are Cooking>";
        String title12_2 = " @@@@--------------@";
        String title13 = "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";

        try {
            final TextGraphics textGraphics = screen.newTextGraphics();

            screen.clear();
            textGraphics.putString(16, 16, title0);
            textGraphics.putString(16, 17, title1);
            textGraphics.putString(15, 18, title2);
            textGraphics.putString(16, 19, title3);
            textGraphics.putString(16, 20, title4);
            textGraphics.putString(16, 21, title5);
            textGraphics.putString(16, 22, title6);
            textGraphics.putString(16, 23, title7);
            textGraphics.putString(16, 24, title8);
            textGraphics.putString(16, 25, title9);
            textGraphics.putString(16, 26, title10);
            textGraphics.putString(16, 27, title11);
            textGraphics.putString(16, 28, title12);
            textGraphics.putString(16+20, 28, title12_1, SGR.BOLD, SGR.BLINK);
            textGraphics.putString(16+20+26, 28, title12_2);
            textGraphics.putString(16, 29, title13);
            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printSpoon(Screen screen, Selectors.SpoonPosition position) {
        String title0="", title1="", title2="", title3="", title4="", title5="", title6="", title7="";
        String title8="", title9="", title10="", title11="", title12="", title13="", title14="", title15="";
        switch (position) {
            case RIGHT -> {
                title0 = "                                                      -=:          \n";
                title1 = "                                                     :==:          \n";
                title2 = "                                                     --:           \n";
                title3 = "                                 .                  :=:.           \n";
                title4 = "                                  .                 -::            \n";
                title5 = "                                 :.                :=:             \n";
                title6 = "                           :    .=     ..          =:.             \n";
                title7 = "                           :.  .=      .-         :=:              \n";
                title8 = "                          .=   =.      ::        .==               \n";
                title9 = "                         .=.   =.     :-         :=:               \n";
                title10 = "                         =.     =    :-         :=-                \n";
                title11 = "                         =       :   ::     .-=====.               \n";
                title12 = "                         -.          .-    -=:======.              \n";
                title13 = "                          :.          .-  =========:=              \n";
                title14 = "                           .            .==========:=              \n";
                title15 = "                                        .==========.-              \n";
            }
            case LEFT -> {
                title0 = "       :=-                                                         \n";
                title1 = "       .==:                                                        \n";
                title2 = "        ::=                                                        \n";
                title3 = "         :-:                                                       \n";
                title4 = "         .:=               .:           :                          \n";
                title5 = "          ::-              .:           -                          \n";
                title6 = "          .:=              -.    :     :-                          \n";
                title7 = "           :--            -.      .   ::                           \n";
                title8 = "            ==           -:      :.  .=                            \n";
                title9 = "            .=-          -.     .-    =                            \n";
                title10 = "             ==:          -    .=     .-                           \n";
                title11 = "             =====-        :   =        :                          \n";
                title12 = "            ======:--          ::                                  \n";
                title13 = "           :-=========          -                                  \n";
                title14 = "           ::==========          .                                 \n";
                title15 = "           ::==========.                                           \n";
            }
            case MIDDLE -> {
                title0 = "                           :   ==                                  \n";
                title1 = "                           :  .==                                  \n";
                title2 = "                          ::  .==      .                           \n";
                title3 = "                         ::    =      :                            \n";
                title4 = "                         =     =      :                            \n";
                title5 = "                        ::     =     ::                            \n";
                title6 = "                         -     =.   ::                             \n";
                title7 = "                     .    :    =-   =                              \n";
                title8 = "                     .:        ==   -.                             \n";
                title9 = "                     -         ==    :                             \n";
                title10 = "                    =          ==     .                            \n";
                title11 = "                   ::         :==:                                 \n";
                title12 = "                   :.       :======.                               \n";
                title13 = "                   .-      :::=====-:                              \n";
                title14 = "                     :    :========--.                             \n";
                title15 = "                          -=========::                             \n";
            }
        }
        try {
            final TextGraphics textGraphics = screen.newTextGraphics();

            textGraphics.putString(16, 1, title0);
            textGraphics.putString(16, 2, title1);
            textGraphics.putString(16, 3, title2);
            textGraphics.putString(16, 4, title3);
            textGraphics.putString(16, 5, title4);
            textGraphics.putString(16, 6, title5);
            textGraphics.putString(16, 7, title6);
            textGraphics.putString(16, 8, title7);
            textGraphics.putString(16, 9, title8);
            textGraphics.putString(16, 10, title9);
            textGraphics.putString(16, 11, title10);
            textGraphics.putString(16, 12, title11);
            textGraphics.putString(16, 13, title12);
            textGraphics.putString(16, 14, title13);
            textGraphics.putString(16, 15, title14);
            textGraphics.putString(16, 16, title15);
            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
