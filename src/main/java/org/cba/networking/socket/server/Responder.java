package org.cba.networking.socket.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by adam on 29/09/2017.
 */
public class Responder extends Observable{
    private List<ClientHandler> clientHandlers = new ArrayList<>();

    public void addEchoClientHandler(ClientHandler clientHandler) {
        clientHandlers.add(clientHandler);
    }

    public void respondToClients(ClientHandler sender, String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (!clientHandler.equals(sender)) {
                clientHandler.getOutput().println(message);
            }
        }
    }
}
