package com.kozhekin.messages;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Implementation of Player
 */
public class PlayerImpl implements Player {

    private final String name;
    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    private final CommunicationContext context;
    private final CommunicationStrategy strategy;

    public PlayerImpl(String name) {
        this.name = name;
        this.context = new CommunicationContextImpl();
        this.strategy = new CommunicationStrategyImpl(this, context);
    }

    public void startCommunication(Link playerLink, Link opponentLink, String initMessage) {
        sendMessage(strategy.createMessage(playerLink, opponentLink, initMessage));
    }

    public void stop() {
        System.out.println(name + " stopped");
        exec.shutdown();
    }

    public void sendMessage(final Message message) {
        exec.submit(() -> {
            message.getReceiver().send(message);
            System.out.println(name + " sent     " + message.getText());
            strategy.onSend(message);
        });
    }

    public void receiveMessage(final Message message) {
        exec.submit(() -> {
            System.out.println(name + " received " + message.getText());
            strategy.onReceive(message);
        });
    }
}
