package com.kozhekin.messages;

/**
 * Strategy keeps logic of communication process, what to send, when to stop
 */
public class CommunicationStrategyImpl implements CommunicationStrategy {
    private final static int MAX_MESSAGES = 10;
    private final CommunicationContext context;
    private final Player player;

    public CommunicationStrategyImpl(Player player, CommunicationContext context) {
        this.context = context;
        this.player = player;
    }

    public void onSend(Message message) {
        context.onSend(message);
        if (isCommunicationOver()) {
            message.getSender().stop();
        }
    }

    public void onReceive(Message message) {
        context.onReceive(message);
        if (isCommunicationOver()) {
            message.getReceiver().stop();
            return;
        }
        player.sendMessage(
                createMessage(message.getReceiver(), message.getSender(), buildResponse(message)));
    }

    public String buildResponse(Message message) {
        return String.format("%s|%d", message.getText(), context.getSentMessages().size());
    }

    public boolean isCommunicationOver() {
        return context.getReceivedMessages().size() == MAX_MESSAGES
                && context.getSentMessages().size() == MAX_MESSAGES;
    }

    public Message createMessage(Link sender, Link receiver, String text) {
        return new MessageImpl(sender, receiver, text);
    }
}
