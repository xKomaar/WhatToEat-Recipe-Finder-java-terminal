package pl.whatToEat.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class StartPageView {

    public static void printStartPage(Screen screen) throws RuntimeException, InterruptedException {

        String title0 = "oooooo   oooooo     oooo oooo                      .   \n";
        String title1 = " `888.    `888.     .8'  `888                    .o8   \n";
        String title2 = "  `888.   .8888.   .8'    888 .oo.    .oooo.   .o888oo \n";
        String title3 = "   `888  .8'`888. .8'     888P\"Y88b  `P  )88b    888   \n";
        String title4 = "    `888.8'  `888.8'      888   888   .oP\"888    888   \n";
        String title5 = "     `888'    `888'       888   888  d8(  888    888 . \n";
        String title6 = "      `8'      `8'       o888o o888o `Y888\"\"8o   \"888\" \n";

        String title7 = "                 ooooooooooooo                         \n";
        String title8 = "                 8'   888   `8                         \n";
        String title9 = "                      888       .ooooo.                \n";
        String title10 = "                      888      d88' `88b               \n";
        String title11 = "                      888      888   888               \n";
        String title12 = "                      888      888   888               \n";
        String title13 = "                     o888o     `Y8bod8P'               \n";

        String title14 = "               oooooooooooo               .         \n";
        String title15 = "               `888'     `8             .o8         \n";
        String title16 = "                888          .oooo.   .o888oo       \n";
        String title17 = "                888oooo8    `P  )88b    888         \n";
        String title18 = "                888    \"     .oP\"888    888       \n";
        String title19 = "                888       o d8(  888    888 .       \n";
        String title20 = "               o888ooooood8 `Y888\"\"8o   \"888\"   \n";

        try {
            int maxY = screen.getTerminalSize().getRows();

            final TextGraphics textGraphics = screen.newTextGraphics();


            for (int i = 1; i < maxY-1; i++) {
                screen.clear();
                textGraphics.setForegroundColor(new TextColor.RGB(250, 171, 62));
                textGraphics.putString(20, maxY - i, title0);
                textGraphics.putString(20, maxY - i + 1, title1);
                textGraphics.putString(20, maxY - i + 2, title2);
                textGraphics.putString(20, maxY - i + 3, title3);
                textGraphics.setForegroundColor(new TextColor.RGB(130,230,1));
                textGraphics.putString(20, maxY - i + 4, title4);
                textGraphics.putString(20, maxY - i + 5, title5);
                textGraphics.setForegroundColor(new TextColor.RGB(255,255,1));
                textGraphics.putString(20, maxY - i + 6, title6);
                textGraphics.putString(20, maxY - i + 8, title7);
                textGraphics.putString(20, maxY - i + 9, title8);
                textGraphics.setForegroundColor(new TextColor.RGB(163,56,21));
                textGraphics.putString(20, maxY - i + 10, title9);
                textGraphics.putString(20, maxY - i + 11, title10);
                textGraphics.putString(20, maxY - i + 12, title11);
                textGraphics.putString(20, maxY - i + 13, title12);
                textGraphics.setForegroundColor(new TextColor.RGB(236,53,33));
                textGraphics.putString(20, maxY - i + 14, title13);
                textGraphics.putString(20, maxY - i + 16, title14);
                textGraphics.putString(20, maxY - i + 17, title15);
                textGraphics.putString(20, maxY - i + 18, title16);
                textGraphics.setForegroundColor(new TextColor.RGB(250, 171, 62));
                textGraphics.putString(20, maxY - i + 19, title17);
                textGraphics.putString(20, maxY - i + 20, title18);
                textGraphics.putString(20, maxY - i + 21, title19);
                textGraphics.putString(20, maxY - i + 22, title20);
                screen.refresh();
                Thread.sleep(80);
            }
            Thread.sleep(500);
            textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
            textGraphics.putString(39, 28, "<Press ENTER to Start>", SGR.BOLD, SGR.BLINK);
            screen.refresh();

            KeyStroke keyStroke = screen.readInput();
            while(keyStroke.getKeyType() != KeyType.Enter && keyStroke.getKeyType() != KeyType.EOF) {
                keyStroke = screen.readInput();
            }
            if(keyStroke.getKeyType() == KeyType.EOF) {
                screen.close();
                System.exit(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
