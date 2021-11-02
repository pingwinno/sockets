package com.study.sockets.multithreading;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 3000)) {
            System.out.println("Connected");
            System.out.println("Type message");
            var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var consoleReader = new BufferedReader(new InputStreamReader(System.in));
            var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line = "";
            while ((line = consoleReader.readLine()) != null) {
                writer.write(line + "\n");
                writer.flush();
                line = reader.readLine();
                System.out.println(line);
            }
            reader.close();
            writer.close();
        }
    }
}
