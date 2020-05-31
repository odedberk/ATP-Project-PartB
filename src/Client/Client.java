package Client;

import java.io.IOException;
import java.net.*;

/**
 * a client that can connect to a server and act corresponding to
 * an injected strategy
 */
public class Client implements Runnable {
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
            Socket socket = new Socket(address,serverPort);//try to connect to the server
            System.out.println("Client is connected to server!");
            clientStrategy.clientStrategy(socket.getInputStream(),socket.getOutputStream());//send the Soket input and output to the strategy
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
