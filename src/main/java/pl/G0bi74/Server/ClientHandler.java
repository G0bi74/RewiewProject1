package pl.G0bi74.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Server server;
    private Socket socket;
    private Scanner input;
    private PrintWriter output;

    public ClientHandler(Socket socket, Server server)  {
        this.server = server;
        this.socket = socket;

        try {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void send(String message) { output.println(message); }

    @Override
    public void run() {
        String login = input.nextLine();
        server.addClient(login, this);
        server.printClients();
        server.broadcast("Server: "+ login + " Joined.");

        String message;

        do{
            message = input.nextLine();
            if(message.startsWith("/")){
                String priv[] = message.split(" ",2);
                if(server.isClient(priv[0].substring(1))){
                    server.privateMessage(priv[0].substring(1), priv[1]);
                }else{
                    output.println("There is no such client on the server.");
                }
            }else{
                server.broadcast(login+": "+message);
            }

        }while(!message.equalsIgnoreCase("bye"));
        server.removeClient(login);
        server.broadcast("Server: "+ login + " left.");
        try{
            socket.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
