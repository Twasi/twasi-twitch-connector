package net.twasi.twitchconnector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties values = new Properties();

    public static boolean load() {
        try {
            values.load(new FileInputStream(new File("twasi-twitch-connector.properties")));
            return true;
        } catch (FileNotFoundException e) {
            Logger.log.info("Config file not found.");
            return false;
        } catch (IOException e) {
            Logger.log.error("Cannot read config file", e);
            return false;
        }
    }

    public static Properties getProperties() {
        return values;
    }
}
