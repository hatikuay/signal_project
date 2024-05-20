package com;

import com.data_access.FileDataListener;
import com.data_access.TCPDataListener;

public class Main {
    public static void main(String[] args) {
        String directoryPath = "data";  // specify the directory to be monitored
        FileDataListener fileDataListener = new FileDataListener(directoryPath);
        TCPDataListener tcpDataListener = new TCPDataListener(5000);
        // Start listening for file changes
        //fileDataListener.startListening();
        tcpDataListener.startListening();
        
    }
}

