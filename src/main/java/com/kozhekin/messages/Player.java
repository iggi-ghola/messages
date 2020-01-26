package com.kozhekin.messages;

/**
 * Main class combines context and strategy to hold communication process
 */

public interface Player {
    void startCommunication(Link player, Link opponent, String initMessage);

    void stop();

    void sendMessage(Message message);

    void receiveMessage(Message message);
}
