package com.study.sockets.multithreading;

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
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    System.out.println("Open new connection. Client: " + socket.getPort());
                    createConnection(socket);
                    System.out.println("Close connection. Client: " + socket.getPort());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    private static void createConnection(Socket socket) throws IOException {
        try (socket; var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Inbound message: " + line + " Client:" + socket.getPort());
                writer.write(("Echo: " + line + "\n"));
                writer.flush();
            }
        }
    }
}
