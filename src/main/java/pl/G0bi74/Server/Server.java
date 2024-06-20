package pl.G0bi74.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private ServerSocket serverSocket;
    private Map<String, ClientHandler> clients = new HashMap<>();

    public Map<String, ClientHandler> getClients() {return clients;}
    public ServerSocket getServerSocket() {return serverSocket;}
    public void addClient(String name, ClientHandler handler) {clients.put(name, handler);}
    public void removeClient(String name) {clients.remove(name);}
    public void printClients() {System.out.println(clients);}

    public void broadcast(String message) {
        for (ClientHandler handler : clients.values()) {
            handler.send(message);
        }
    }

    public void start(int port){
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket,this);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}
