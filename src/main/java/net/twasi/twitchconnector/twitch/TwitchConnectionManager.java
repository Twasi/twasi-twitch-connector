package net.twasi.twitchconnector.twitch;

import java.util.ArrayList;
import java.util.List;

public class TwitchConnectionManager {
    public List<TwitchConnection> connections = new ArrayList<>();

    public boolean add(String twitchId, String twitchName) {
        TwitchConnection conn = new TwitchConnection(twitchId, twitchName);
        connections.add(conn);

        return conn.connect();
    }

    public boolean remove(final String twitchId) {
        TwitchConnection conn = connections.stream().filter(con -> con.getTwitchId().equalsIgnoreCase(twitchId)).findFirst().orElse(null);

        if (conn == null) {
            return false;
        }

        connections.remove(conn);
        return conn.disconnect();
    }
}
