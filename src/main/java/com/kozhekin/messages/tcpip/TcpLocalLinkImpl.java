package com.kozhekin.messages.tcpip;

import com.kozhekin.messages.Message;
import com.kozhekin.messages.MessageImpl;
import com.kozhekin.messages.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * Implementation of transport level of separate-process-communication
 * Uses Server Sockets to listen to the incoming messages
 * Format of messages: host\nport\ntext - is not optimal, was chosen for simplicity
 */
public class TcpLocalLinkImpl extends TcpLink {
    private final Consumer<Message> onReceive;
    private final Runnable onStop;
    private final ExecutorService serverExec = initExec(false, "NetServer");

    public TcpLocalLinkImpl(Player player, String host, int port) {
        super(host, port);
        this.onReceive = player::receiveMessage;
        this.onStop = player::stop;
        initServer();
    }


    @Override
    public void send(Message message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void receive(Message message) {
        onReceive.accept(message);
    }

    @Override
    public void stop() {
        try {
            new Socket(host, port).getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopFromNet() {
        serverExec.shutdown();
        onStop.run();
    }

    private void initServer() {
        serverExec.submit(() -> {
            try (ServerSocket ss = new ServerSocket(port)) {
                while (true) {
                    try (Socket s = ss.accept()) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        String host = reader.readLine();
                        String port = reader.readLine();
                        String text = reader.readLine();
                        if (host == null || host.length() == 0 || port.length() == 0) {
                            stopFromNet();
                            return;
                        }
                        Message message = new MessageImpl(new TcpRemoteLinkImpl(host, Integer.parseInt(port)), this, text);
                        receive(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
