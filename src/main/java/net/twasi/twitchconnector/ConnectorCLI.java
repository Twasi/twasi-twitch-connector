package net.twasi.twitchconnector;

import net.twasi.twitchconnector.twitch.TwitchConnectionManager;

import java.util.Scanner;

public class ConnectorCLI {
    private TwitchConnectionManager manager;

    public ConnectorCLI(TwitchConnectionManager manager) {
        this.manager = manager;
    }

    public void run() {
        String input;
        Scanner scanner = new Scanner(System.in);

        while(true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("stats")) {
                System.out.println("=============== Current Stats ===============");
            }
        }
    }

}
