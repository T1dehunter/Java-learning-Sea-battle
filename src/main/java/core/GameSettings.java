package core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameSettings {
    private String fileName;

    public GameSettings(String fileName) {
        this.fileName = fileName;
    }

    public Properties getSettings() {
        Properties settings = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream(fileName);

            settings.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return settings;
    }
}
