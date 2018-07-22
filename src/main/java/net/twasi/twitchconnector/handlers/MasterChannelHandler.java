package net.twasi.twitchconnector.handlers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import net.twasi.twitchconnector.twitch.TwitchConnectionManager;

import java.io.IOException;

public class MasterChannelHandler extends DefaultConsumer {
    public MasterChannelHandler(Channel channel, TwitchConnectionManager manager) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("Got: " + new String(body, "UTF-8"));
    }
}
