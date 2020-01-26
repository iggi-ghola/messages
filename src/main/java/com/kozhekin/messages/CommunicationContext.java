package com.kozhekin.messages;

import java.util.List;

/**
 * Responsible for storing messages.
 * It keeps the state of communication
 */
public interface CommunicationContext {
    List<Message> getSentMessages();

    List<Message> getReceivedMessages();

    void onSend(Message message);

    void onReceive(Message message);

}
