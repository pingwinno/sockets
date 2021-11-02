package com.study.sockets.streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(3000);
        try (Socket socket = serverSocket.accept()) {
            var buffer = new byte[50];
            var inputStream = new BufferedInputStream(socket.getInputStream());
            var outputStream = new BufferedOutputStream(socket.getOutputStream());
            int counter;
            while ((counter = inputStream.read(buffer)) != -1) {
                var receivedMessage = new String(buffer, 0, counter);
                System.out.println("Inbound message: " + receivedMessage);
                outputStream.write(("Echo: " + receivedMessage).getBytes());
                outputStream.flush();
            }
        }
    }
}
