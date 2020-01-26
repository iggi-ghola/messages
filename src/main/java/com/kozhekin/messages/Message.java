package com.kozhekin.messages;

/**
 * Keeps data of communication
 */

public interface Message {
    Link getSender();

    Link getReceiver();

    String getText();
}
