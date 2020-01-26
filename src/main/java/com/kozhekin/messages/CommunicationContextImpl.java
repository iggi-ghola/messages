package com.kozhekin.messages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple implementation of  context, only keeps messages in-memory
 */
public class CommunicationContextImpl implements CommunicationContext {

    private final List<Message> sentMessages = new ArrayList<>();
    private final List<Message> receivedMessages = new ArrayList<>();


    public List<Message> getSentMessages() {
        return Collections.unmodifiableList(sentMessages);
    }

    public List<Message> getReceivedMessages() {
        return Collections.unmodifiableList(receivedMessages);
    }

    public void onSend(Message message) {
        sentMessages.add(message);
    }

    public void onReceive(Message message) {
        receivedMessages.add(message);
    }
}
