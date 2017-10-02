package org.cba.networking.socket.client;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by adam on 27/09/2017.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        final Socket serverSocket = new Socket("207.154.217.117", 1234);
        executor.submit(new ConsoleInputRunnable(serverSocket));
        executor.submit(new ServerInputRunnable(serverSocket));
        executor.shutdown();
    }


}
