package com.kozhekin.messages;

/**
 * Makes decision when should to stop communication
 */

public interface CommunicationStrategy {
    void onSend(Message message);

    void onReceive(Message message);

    boolean isCommunicationOver();

    Message createMessage(Link sender, Link receiver, String text);
}
