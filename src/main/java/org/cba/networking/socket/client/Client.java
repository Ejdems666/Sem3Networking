package org.cba.networking.socket.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by adam on 27/09/2017.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        final Socket server = new Socket("localhost", 1234);
        Thread consoleInputThread = new Thread(() -> {
            try {
                PrintWriter serverOutput = new PrintWriter(server.getOutputStream(), true);
                Scanner consoleInput = new Scanner(System.in);
                while (server.isConnected()) {
                    String message = consoleInput.nextLine();
                    serverOutput.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread serverInputThread = new Thread(() -> {
            try {
                Scanner serverInput = new Scanner(server.getInputStream());
                while (server.isConnected()) {
                    System.out.println(serverInput.nextLine());
                }
                System.out.println("done");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        consoleInputThread.start();
        serverInputThread.start();
    }
}
