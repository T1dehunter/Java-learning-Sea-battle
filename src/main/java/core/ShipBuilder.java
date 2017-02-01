package core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="ShipBuilder", namespace="ShipBuilder")
@XmlAccessorType(XmlAccessType.FIELD)
class ShipBuilder {
    public static class ShipSetting {
        @XmlElement(name = "type")
        private String type;

        @XmlElement(name = "count")
        private int count;

        @XmlElement(name = "length")
        private int length;

        @XmlElement(name = "color")
        private String color;

        public String test() {
            return "\n type: " + type + "| count: " + count + "| length: " + length + "| color: " + color;
        }
    }


    @XmlElement(name="ShipSetting")
    private List<ShipSetting> listSettings;


    public ShipSetting getShipByType() {
        return listSettings.get(1);
    }

    public ArrayList<Ship> buildListShips() {
        ArrayList<Ship> result = new ArrayList<>();

        for (ShipSetting setting: listSettings) {
            for (int i = 0; i < setting.count; i++) {
                Ship ship = new Ship(setting.length, setting.color);
                result.add(ship);
            }
        }

        return result;
    }
}
