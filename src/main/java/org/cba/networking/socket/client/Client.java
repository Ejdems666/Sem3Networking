package org.cba.networking.socket.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by adam on 27/09/2017.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(server.getOutputStream(), true);
        Scanner input = new Scanner(server.getInputStream());
        Scanner consoleInput = new Scanner(System.in);
        while (server.isConnected()) {
            try {
                String message = consoleInput.nextLine();
                output.println(message);
                System.out.println("Server: " + input.nextLine());
            } catch (NoSuchElementException e) {
                System.out.println("Server disconnected");
            }
        }
    }
}
