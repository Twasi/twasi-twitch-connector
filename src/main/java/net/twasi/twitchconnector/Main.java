package net.twasi.twitchconnector;

import net.twasi.twitchconnector.twitch.TwitchConnectionManager;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) throws IOException, TimeoutException {
        // Load config
        if (!Config.load()) {
            System.exit(1);
        }

        TwitchConnectionManager manager = new TwitchConnectionManager();

        Controller ctrl = new Controller();
        ctrl.connect(manager);

        new ConnectorCLI(manager).run();
    }
}
