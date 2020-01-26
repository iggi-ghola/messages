package com.kozhekin.messages.tcpip;

import com.kozhekin.messages.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *  Implementation of transport level of separate-process-communication
 *  Keeps host and port (connectivity details) of opponent player
 *  Uses client Sockets to send messages
 *  Format of messages: host\nport\ntext - is not optimal, was chosen for simplicity
 *
 */
public class TcpRemoteLinkImpl extends TcpLink {

    public TcpRemoteLinkImpl(String host, int port) {
        super(host, port);
    }

    @Override
    public String getInfo() {
        return String.format("%s\n%d\n", host, port);
    }

    @Override
    public void send(Message message) {
        send(message.getSender().getInfo() + message.getText());
    }

    private void send(String str) {
        try (Socket s = new Socket(host, port)) {
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            pw.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receive(Message message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void stop() {
        try {
            send("\n\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
