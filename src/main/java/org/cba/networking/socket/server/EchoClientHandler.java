package org.cba.networking.socket.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by adam on 27/09/2017.
 */
public class EchoClientHandler implements Runnable {
    private final Socket client;
    private final String name;
    private static HashMap<String, String> dictionary = new HashMap<>();
    static {
        dictionary.put("hund","dog");
    }

    public EchoClientHandler(Socket client, String name) {
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
                message = processMessage(message);
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

    private String processMessage(String message) {
        String[] splitMessage = message.split("#");
        String command = "ECHO";
        if (splitMessage.length == 2) {
            command = splitMessage[0];
            message = splitMessage[1];
        }
        switch (command) {
            case "ECHO":
                return message;
            case "UPPER":
                return message.toUpperCase();
            case "LOWER":
                return message.toLowerCase();
            case "REVERSE":
                return new StringBuilder(message).reverse().toString();
            case "TRANSLATE":
                message = dictionary.get(message);
                if (message == null) {
                    message = "404 translation not found!";
                }
        }
        return message;
    }
}
