package org.cba.networking.socket.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by adam on 02/10/2017.
 */
public class ServerInputRunnable implements Runnable {
    final Socket serverSocket;

    public ServerInputRunnable(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            Scanner serverInput = new Scanner(serverSocket.getInputStream());
            while (serverSocket.isConnected()) {
                System.out.println(serverInput.nextLine());
            }
            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
