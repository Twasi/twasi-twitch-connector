package net.twasi.twitchconnector.twitch.irc;

import net.twasi.twitchconnector.Config;
import net.twasi.twitchconnector.Logger;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TwitchIRCClient {
    private String userName;
    private String botName;
    private String botAuth;

    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;

    // State
    private boolean isConnected = false;

    private Thread messageReader;

    public TwitchIRCClient(String userName) {
        this.userName = userName;
    }

    public TwitchIRCClient(String userName, String botName, String botAuth) {
        this.userName = userName;
        this.botName = botName;
        this.botAuth = botAuth;
    }

    public boolean connect() {
        try {
            socket = new Socket(Config.getProperties().getProperty("twitch.hostname"), Integer.valueOf(Config.getProperties().getProperty("twitch.port")));

            // Open writer and reader
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Connect
            this.writer.write("PASS " + botAuth + "\n");
            this.writer.write("NICK " + botName + "\n");
            this.writer.write("CAP REQ :twitch.tv/commands\n");
            this.writer.write("CAP REQ :twitch.tv/membership\n");
            this.writer.write("CAP REQ :twitch.tv/tags\n");
            this.writer.write("JOIN #" + botName + "\n");
            this.writer.flush();

            String line = "";
            while ((line = this.reader.readLine()) != null) {
                Logger.log.trace(line);
                if (line.contains("004")) {
                    Logger.log.debug("Connected: " + userName + " (Account: " + botName + ")");
                    isConnected = true;
                    return true;
                }
            }
            return false;
        } catch (UnknownHostException e) {
            Logger.log.error("Cannot connect to twitch IRC, host unknown", e);
            return false;
        } catch (IOException e) {
            Logger.log.error("Cannot connect to twitch IRC", e);
            return false;
        }
    }

    public boolean disconnect() {
        try {
            writer.close();
            reader.close();
            socket.close();
            isConnected = false;
            Logger.log.debug("Disconnected: " + userName + " (Account: " + botName + ")");
            return true;
        } catch (Exception e) {
            Logger.log.error("Failed to disconnect from Twitch IRC.", e);
            return false;
        }
    }

    public boolean isConnected() {
        return this.isConnected;
    }

}
