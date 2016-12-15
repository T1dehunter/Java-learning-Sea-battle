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

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Petya"));
        players.add(new Player("Vasya"));

        Core core = new Core(new GuiBuilder(), players);
        core.run();
    }
}
