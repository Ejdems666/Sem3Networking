package org.cba.networking.socket.client;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by adam on 10/2/2017.
 */
public class GUIClient {
    private JButton sendButton;
    private JTextField inputField;
    private JTextPane output;
    private JPanel mainPanel;
    final Socket serverSocket;

    public GUIClient(Socket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        PrintWriter serverOutput = new PrintWriter(serverSocket.getOutputStream(), true);
        new Thread(() -> {
            sendButton.addActionListener(e -> {
                printLineToTextField(inputField.getText());
                serverOutput.println(inputField.getText());
                inputField.setText("");
            });
        }).start();
    }

    private void printLineToTextField(String text) {
        output.setText(output.getText() + text + "\n");
    }

    public static void main(String[] args) throws IOException {
        final Socket serverSocket = new Socket("localhost", 1234);

        JFrame frame = new JFrame("GUI Client");
        GUIClient guiClient = new GUIClient(serverSocket);
        frame.setContentPane(guiClient.mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(guiClient.sendButton);

        new Thread(() -> {
            try {
                Scanner serverInput = new Scanner(serverSocket.getInputStream());
                while (serverSocket.isConnected()) {
                    guiClient.printLineToTextField(serverInput.nextLine());
                }
                guiClient.printLineToTextField("done");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
