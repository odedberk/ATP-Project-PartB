package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
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

    /**
     * start the server in a new Thread to allow main program to continue running
     */
    public void start() {
       new Thread(this).start();
    }

    /**
     * handle a new client that was connected to the server
     * @param clientSocket
     */
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

    /**
     *handle the server thread
     */
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(interval);//whit an interval seconds while clients "arrives"
            threadPoolExecutor = Executors.newFixedThreadPool(5);
            while (!stop)
            {
                try {
                    Socket clientSocket = serverSocket.accept();//get new client
                    threadPoolExecutor.execute(() -> clientHandle(clientSocket));// send to the thread pool
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
    public static class Configurations {
        private static Properties prop;

        private static void initProp() {
            try (InputStream input = Configurations.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null)
                    return;
                prop = new Properties();
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static String getProperty(String name) {
            if (prop == null)
                initProp();
            return prop.getProperty(name);
        }

        public static void main(String[] args) {
            System.out.println(getProperty("test"));
            System.out.println(System.getProperty("java.io.tmpdir"));
        }
    }
}
