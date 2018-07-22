package net.twasi.twitchconnector.twitch;

import net.twasi.twitchconnector.twitch.irc.TwitchIRCClient;

public class TwitchConnection {
    private String twitchId;
    private String twitchName;

    private TwitchIRCClient client;

    public TwitchConnection(String twitchId, String twitchName) {
        this.twitchId = twitchId;
        this.twitchName = twitchName;

        this.client = new TwitchIRCClient(twitchName);
    }

    public TwitchConnection(String twitchId, String twitchName, String botName, String botAuth) {
        this.twitchId = twitchId;
        this.twitchName = twitchName;

        this.client = new TwitchIRCClient(twitchName, botName, botAuth);
    }

    public boolean connect() {
        return this.client.connect();
    }

    public boolean disconnect() {
        return this.client.disconnect();
    }

    public boolean isConnected() {
        return this.client.isConnected();
    }

    public String getTwitchId() {
        return twitchId;
    }
}
