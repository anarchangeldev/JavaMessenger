package Reciever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Reciever {

    private boolean isRunning = true;

    public Reciever(int listeningPort) throws IOException {

        Runnable receiverThread = new Runnable() {
            public void run() {

                ServerSocket serverSocket;
                try {
                    serverSocket = new ServerSocket(listeningPort);
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    while (isRunning) {
                        try {
                            System.out.println(in.readLine());
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        };

        new Thread(receiverThread).start();
    }

    public void stop() {
        isRunning = false;
    }

}
