package Server;

import org.junit.jupiter.api.parallel.Execution;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{

    private int port;//The port
    private int interval;
    private IServerStrategy serverStrategy;//The strategy for handling clients
    private volatile boolean stop;


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
            System.out.println("recieved streams"); //DEBUG
            this.serverStrategy.handleClient(inputClient, outputClient);
            inputClient.close();
            outputClient.close();
            clientSocket.close();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        System.out.println("The server has stopped!");
        this.stop = true;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(interval);
//            String poolMax = Configurations.getProperty("pool"); //DEBUG
//            ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(Integer.parseInt(poolMax)); //DEBUG
            ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
            while (!stop)
            {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Server : connected to client");
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
}
