package Client;

import java.io.IOException;
import java.net.*;

public class Client extends Thread {
    private InetAddress address;
    private int serverPort;
    private IClientStrategy clientStrategy;

    public Client(InetAddress address, int serverPort, IClientStrategy clientStrategy) {
        this.address = address;
        this.serverPort = serverPort;
        this.clientStrategy = clientStrategy;
    }

    public void communicateWithServer() {
        run();
    }

    public void run()
    {
        try {
            Socket socket = new Socket(address,serverPort);
            System.out.println("Client is connected to server!");
            clientStrategy.clientStrategy(socket.getInputStream(),socket.getOutputStream());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
