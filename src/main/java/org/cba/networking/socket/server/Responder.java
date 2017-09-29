package org.cba.networking.socket.server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 29/09/2017.
 */
public class Responder {
    private List<EchoClientHandler> clientHandlers = new ArrayList<>();

    public void addEchoClientHandler(EchoClientHandler echoClientHandler) {
        clientHandlers.add(echoClientHandler);
    }

    public void respondToClients(String message) {
        for (EchoClientHandler clientHandler : clientHandlers) {
            clientHandler.getOutput().println(message);
        }
    }
}
