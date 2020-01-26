package com.kozhekin.messages;

import java.util.function.Consumer;

/**
 * Implementation of transport level of same-process-communication
 * In fact class keeps no logic
 */
public class LinkImpl implements Link {

    private final Consumer<Message> onReceive;
    private final Runnable onStop;

    public LinkImpl(Player player) {
        this.onReceive = player::receiveMessage;
        this.onStop = player::stop;
    }

    @Override
    public void send(Message message) {
        message.getReceiver().receive(message);
    }

    @Override
    public void receive(Message message) {
        onReceive.accept(message);
    }

    @Override
    public void stop() {
        onStop.run();
    }

    @Override
    public String getInfo() {
        return "";
    }

}
