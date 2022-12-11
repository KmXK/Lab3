package by.bsuir.vt3.client.main;

import by.bsuir.vt3.beans.Response;
import by.bsuir.vt3.client.controller.ClientController;
import by.bsuir.vt3.client.controller.impl.ClientControllerImpl;
import lombok.Getter;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Getter
public class Client extends Thread {
    private int port;

    @Override
    public void run() {
        try {
           port = 6666;
            Socket sock = new Socket("localhost", port);
            ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
            ClientController clientController = new ClientControllerImpl();
            while (true) {
                outputStream.writeObject(clientController.createRequest());
                clientController.processResponse((Response) inputStream.readObject());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
