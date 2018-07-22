package net.twasi.twitchconnector;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import net.twasi.twitchconnector.handlers.MasterChannelHandler;
import net.twasi.twitchconnector.twitch.TwitchConnectionManager;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Controller {
    public static final String MASTER_CTRL_NAME = "MASTER_CTRL";
    public static final String MESSAGE_IN_NAME = "MESSAGE_IN";
    public static final String MESSAGE_OUT_NAME = "MESSAGE_OUT";

    private ConnectionFactory factory;
    private Connection connection;
    private Channel masterControlChannel;
    private Channel messageInChannel;
    private Channel messageOutChannel;

    private TwitchConnectionManager manager;

    public void connect(TwitchConnectionManager manager) throws IOException, TimeoutException {
        factory  = new ConnectionFactory();
        factory.setHost(Config.getProperties().getProperty("rabbitmq.host"));
        factory.setUsername(Config.getProperties().getProperty("rabbitmq.username"));
        factory.setPassword(Config.getProperties().getProperty("rabbitmq.password"));
        connection = factory.newConnection();

        this.manager = manager;

        // Create and declare default channels
        masterControlChannel = connection.createChannel();
        masterControlChannel.queueDeclare(MASTER_CTRL_NAME, false, false, false, null);
        masterControlChannel.basicConsume(MASTER_CTRL_NAME, true, new MasterChannelHandler(masterControlChannel, manager));

        messageInChannel = connection.createChannel();
        messageInChannel.queueDeclare(MESSAGE_IN_NAME, false, false, false, null);

        messageOutChannel = connection.createChannel();
        messageOutChannel.queueDeclare(MESSAGE_OUT_NAME, false, false, false, null);
    }
}
