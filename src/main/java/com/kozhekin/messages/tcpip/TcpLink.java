package com.kozhekin.messages.tcpip;

import com.kozhekin.messages.Link;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract public class TcpLink implements Link {

    protected final String host;
    protected final int port;

    public TcpLink(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public String getInfo() {
        return String.format("%s\n%d\n", host, port);
    }

    public static ExecutorService initExec(boolean daemon, String name) {
        return Executors.newSingleThreadExecutor(r -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setName(name);
            t.setDaemon(daemon);
            return t;
        });
    }

}
