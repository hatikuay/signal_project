package com;

import com.data_access.WebSocketDataListener;

public class Main {
    public static void main(String[] args) {
        int port = 8080;
        String serverUri = "ws://localhost:" + port;


        // Start the WebSocket listener as a client
        WebSocketDataListener listener = new WebSocketDataListener(serverUri);
        listener.startListening();

   
    }
}
