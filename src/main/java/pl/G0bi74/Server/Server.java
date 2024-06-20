package pl.G0bi74.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private ServerSocket serverSocket;
    private Map<String, ClientHandler> clients = new HashMap<>();

    public Server() throws IOException {
        serverSocket = new ServerSocket(3000);
    }
    public boolean isClient(String name) {
        for( String s : clients.keySet()) {
            if(s.equals(name)) return true;
        } return false;
    }

    public Map<String, ClientHandler> getClients() {
        return clients;
    }
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    public void addClient(String login, ClientHandler clientHandler) {
        clients.put(login, clientHandler);
    }
    public void removeClient(String login) {
        clients.remove(login);
    }
    public void printClients() {
        System.out.println(clients.keySet());
    }
    public void broadcast(String message){
        for (ClientHandler clientHandler : clients.values()) {
            clientHandler.send(message);
        }
    }
    public void privateMessage(String login, String message) {
        ClientHandler clientHandler = clients.get(login);
        if (clientHandler != null) {
            message = "Private MSG from "+login + ": " + message;
            clientHandler.send(message);
        }
    }
    public void start(int port){
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket, this);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
/*
    public void listen() throws IOException {
        System.out.println("Server Started");
        while (true) {
            Socket socket = serverSocket.accept();
            ClientHandler handler = new ClientHandler(socket, this);
            Thread thread = new Thread(handler);
            thread.start();

        }


    }

    public void serverClient() throws IOException {
        Socket socket = serverSocket.accept();

        InputStream in = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        OutputStream out = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(out,true);

        String message;

        writer.println("Hello!");
        while ((message = reader.readLine()) != null){
            writer.println(message);
        }
        socket.close();
    }

    public void removeHandler(ClientHandler clientHandler) {
        handlers.remove(clientHandler);
    }
    public void disconnectHandlers() {
        handlers.forEach(handler -> handler.send("Bye!"));
        handlers.clear();
    }
 */

}