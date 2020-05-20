package Server;

import org.junit.jupiter.api.parallel.Execution;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int port;//The port
    private IServerStrategy serverStrategy;//The strategy for handling clients
    private volatile boolean stop;


    public Server(int i, int i1, IServerStrategy strategy) {
        port = i;
        serverStrategy = strategy;
        stop = false;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            //serverSocket.setSoTimeout(1000);
            String poolMax = Configurations.getProperty("pool");
            ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(Integer.parseInt(poolMax));
            while (!stop)
            {
                try {
                    Socket clientSocket = serverSocket.accept();
                    threadPoolExecutor.execute(() ->{
                        clientHandle(clientSocket);
                    });
                }
                catch (IOException e) {
                    System.out.println("Where are the clients??");
                }
            }
            serverSocket.close();
            threadPoolExecutor.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clientHandle(Socket clientSocket) {
        try {
            InputStream inputClient = clientSocket.getInputStream();
            OutputStream outputClient = clientSocket.getOutputStream();
            this.serverStrategy.handleClient(inputClient, outputClient);
            inputClient.close();
            outputClient.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        System.out.println("The server has stopped!");
        this.stop = true;
    }
}
