package com.data_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TCPDataListener implements DataListener {

    private int port;
    private ServerSocket serverSocket;
    private ExecutorService executor;
    private boolean isRunning;

    public TCPDataListener(int port) {
        this.port = port;
    }

    @Override
    public void startListening() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("TCP Server started on port: " + port);
            isRunning = true;
            executor = Executors.newFixedThreadPool(10);
            executor.submit(this::acceptConnections);

        } catch (IOException e) {
            System.out.println("Error starting TCP server on port: " + port);
            e.printStackTrace();
        }

    }

    @Override
    public void stopListening() {
        isRunning = false;
        if (executor != null) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
        if (serverSocket != null) {
            try {
                serverSocket.close();

            } catch (IOException e) {
                System.out.println("Error closing server socket");
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onDataReceived(String rawData) {
        System.out.println("Data received over TCP: " + rawData);

    }

    private void acceptConnections() {
        while (isRunning) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                executor.submit(() -> handleClient(clientSocket));

            } catch (IOException e) {
                if (isRunning) {
                    System.out.println("Error accepting client connection");
                    e.printStackTrace();
                }
            }

        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String data;
            while ((data = in.readLine()) != null) {
                onDataReceived(data);
            }

        } catch (IOException e) {
            System.out.println("Error reading from client socket");
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Client connection closed: " + clientSocket.getInetAddress());

            } catch (IOException e) {
                System.out.println("Error closing client socket");
                e.printStackTrace();
            }
        }
    }
}
