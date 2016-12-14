import adapters.GUIAppAdapter;
import core.Core;
import core.Player;
import gui.GameScreen;
//import gui.TestGuiElements;
import gui.BattleField;
import gui.GuiBuilder;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        System.out.print("HELLO WORLD!!!!! -> ");


        GuiBuilder guiBuilder = new GuiBuilder();

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Vasya"));
        players.add(new Player("Petya"));

        Core core = new Core(guiBuilder, players);
        //guiBuilder.build();

        TestClasses test = new TestClasses();
        test.run();

    }
}
