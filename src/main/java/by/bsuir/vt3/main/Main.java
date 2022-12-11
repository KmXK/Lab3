package by.bsuir.vt3.main;

import by.bsuir.vt3.client.main.Client;
import by.bsuir.vt3.server.main.Server;

public class Main{
    public static void main(String[] args) {
        var server = new Server();
        var client = new Client();

        server.start();
        client.start();
    }
}
