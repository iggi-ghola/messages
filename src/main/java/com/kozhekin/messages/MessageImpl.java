package com.kozhekin.messages;

/**
 * Simple implementation of message, keeps info about sender, receiver and text
 */
public class MessageImpl implements Message {
    private final Link sender;
    private final Link receiver;
    private final String text;

    public MessageImpl(Link sender, Link receiver, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
    }

    public Link getSender() {
        return sender;
    }

    public Link getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }
}
