package core;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.Properties;

public class PlayerCreator {
    private Properties gameSettings;

    public PlayerCreator(Properties gameSettings) {
        this.gameSettings = gameSettings;
    }

    public Player create(String playerName) {
        ShipBuilder sb = getShipBuilder();

        return new Player(playerName, sb);
    }

    private ShipBuilder getShipBuilder() {
        ShipBuilder builder;
        try {
            JAXBContext jc = JAXBContext.newInstance(ShipBuilder.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            InputStream xml = getClass().getClassLoader().getResourceAsStream("config/ships.settings");
            builder = (ShipBuilder) unmarshaller.unmarshal(xml);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "ShipBuilder.xsd");
        } catch (Exception e) {
            builder = null;
            e.printStackTrace();
        }

        if (builder != null) {
            CoordsBuilder cb = new CoordsBuilder(Integer.parseInt(gameSettings.getProperty("fieldSize")));
            builder.setCordsBuilder(cb);
        }

        return builder;
    }
}
