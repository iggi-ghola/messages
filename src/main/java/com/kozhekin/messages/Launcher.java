package com.kozhekin.messages;

/**
 * Launcher for same-process
 */
public class Launcher {
    public static void main(String[] args) {
        Player p1 = new PlayerImpl("p1");
        Player p2 = new PlayerImpl("p2");
        p1.startCommunication(new LinkImpl(p1), new LinkImpl(p2), "hi!");
    }
}
