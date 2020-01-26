package com.kozhekin.messages;

/**
 * Responsible for transport level of communication
 */

public interface Link {
    void send(Message message);

    void receive(Message message);

    void stop();

    String getInfo();
}
