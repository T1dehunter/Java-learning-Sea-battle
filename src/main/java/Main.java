import core.*;
//import gui.TestGuiElements;
//import gui.BattleField;
import gui.GuiBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;


public class Main {
    public static final String test = "TEST";

    public static void main(String[] args) {
        System.out.print("START GAMEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE\n");

        TestClasses test = new TestClasses();
        test.run();

        GameSettings settings = new GameSettings("config/config.properties");
        Properties props = settings.getSettings();

        ShipBuilderFactory factory = new ShipBuilderFactory(props);
        ShipBuilder randomBuilderForPlayer1 = factory.build();
        ShipBuilder randomBuilderForPlayer2 = factory.build();
//
        ArrayList<Player> players = new ArrayList<>();

        players.add(new Player("Player1", randomBuilderForPlayer1));
        players.add(new Player("Player2", randomBuilderForPlayer2));

//        return;


        int playerFieldWidth = Integer.parseInt(props.getProperty("width"));
        int playerFieldHeight = Integer.parseInt(props.getProperty("height"));

        GuiBuilder builder = new GuiBuilder(playerFieldWidth, playerFieldHeight);
        Core core = new Core(builder, players, playerFieldWidth, playerFieldHeight);
        builder.setCore(core);
//
        core.run();
    }
}
