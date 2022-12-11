package by.bsuir.vt3.server.main;

import lombok.Getter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Getter
public class Server extends Thread {
    private int port;

    @Override
    public void run() {
        startServer();
    }

    public void startServer() {
        port = 6666;
        try (ServerSocket serverSock = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSock.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
