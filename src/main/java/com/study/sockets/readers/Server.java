package com.study.sockets.readers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        try (Socket socket = serverSocket.accept()) {
            var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Inbound message: " + line);
                writer.write(("Echo: " + line + "\n"));
                writer.flush();
            }
            reader.close();
            writer.close();
        }
    }
}
