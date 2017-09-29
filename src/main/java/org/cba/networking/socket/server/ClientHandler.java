package org.cba.networking.socket.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by adam on 27/09/2017.
 */
public class ClientHandler implements Runnable {
    private final Socket client;
    private final String name;

    public ClientHandler(Socket client, String name) {
        this.client = client;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Scanner input = new Scanner(client.getInputStream());
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);
            String message = input.nextLine();
            while (!message.equals("exit")) {
                output.println(name + ": " + message);
                message = input.nextLine();
            }
            output.println("Client "+name+" disconnected");
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.out.println("Client "+name+" disconnected");
        }
        Thread.currentThread().interrupt();
    }
}
