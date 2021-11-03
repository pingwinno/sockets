package com.study.sockets.streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        try (var socket = new Socket("localhost", 3000);
             var outputStream = new BufferedOutputStream(socket.getOutputStream());
             var inputStream = new BufferedInputStream(socket.getInputStream())) {
            System.out.println("Connected");
            System.out.println("Type message");
            int counter;
            var buffer = new byte[50];
            while ((counter = System.in.read(buffer)) != -1) {
                var typedMessage = new String(buffer, 0, counter);
                outputStream.write(typedMessage.getBytes());
                outputStream.flush();
                counter = inputStream.read(buffer);
                System.out.println(new String(buffer, 0, counter));
            }

        }
    }
}
