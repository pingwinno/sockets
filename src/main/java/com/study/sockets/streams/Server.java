package com.study.sockets.streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(3000);
        try (var socket = serverSocket.accept();
             var inputStream = new BufferedInputStream(socket.getInputStream());
             var outputStream = new BufferedOutputStream(socket.getOutputStream())) {
            var buffer = new byte[50];
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
