package core;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.Properties;

public class ShipBuilderFactory {
    private Properties gameSettings;

    public ShipBuilderFactory(Properties gameSettings) {
        this.gameSettings = gameSettings;
    }

    public ShipBuilder build() {
        ShipBuilder sb = getShipBuilder();
        CoordsBuilder cb = new CoordsBuilder(Integer.parseInt(gameSettings.getProperty("width")), Integer.parseInt(gameSettings.getProperty("height")));
        sb.setCordsBuilder(cb);

        return sb;
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

        return builder;
    }
}
