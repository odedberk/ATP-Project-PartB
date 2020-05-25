package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private int port;//The port
    private int interval;
    private IServerStrategy serverStrategy;//The strategy for handling clients
    private volatile boolean stop;
    private ExecutorService threadPoolExecutor;


    public Server(int i, int inter, IServerStrategy strategy) {
        port = i;
        interval=inter;
        serverStrategy = strategy;
        stop = false;
    }

    public void start() {
       new Thread(this).start();
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
        this.stop = true;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(interval);
            threadPoolExecutor = Executors.newFixedThreadPool(5);
            while (!stop)
            {
                try {
                    Socket clientSocket = serverSocket.accept();
                    threadPoolExecutor.execute(() -> clientHandle(clientSocket));
                }
                catch (IOException e) {
                    System.out.println("Where are the clients??");
                }
            }
            System.out.println("The server has stopped!");
            serverSocket.close();
            threadPoolExecutor.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
