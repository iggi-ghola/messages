package com.kozhekin.messages;

import com.kozhekin.messages.tcpip.TcpLocalLinkImpl;
import com.kozhekin.messages.tcpip.TcpRemoteLinkImpl;

/**
 * Launcher for separate-process
 */
public class NetLauncher {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("NetLauncher <port> [<dst port> <message>]");
            return;
        }
        Player p = new PlayerImpl("Player:" + args[0]);
        Link localLink = new TcpLocalLinkImpl(p, "localhost", Integer.parseInt(args[0]));

        if (args.length > 1) {
            Link remoteLink = new TcpRemoteLinkImpl("localhost", Integer.parseInt(args[1]));
            String text = args.length <= 3 ? args[2] : "default message";
            p.startCommunication(localLink, remoteLink, text);
        }
    }
}
