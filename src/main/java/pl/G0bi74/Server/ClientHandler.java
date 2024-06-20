package pl.G0bi74.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    Socket socket;
    Server server;
    Scanner input;
    PrintWriter output;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;

        try {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void send(String message) {output.println(message);}

    @Override
    public void run() {
        output.println("stoi");
    String login = input.nextLine();
    server.addClient(login, this);
    server.printClients();
    server.broadcast("Server: "+ login +" joined.");

    String msg;

    do{
        msg = input.nextLine();
        server.broadcast(login + ": " + msg);
    }while(!msg.equals("bye"));

    server.removeClient(login);
    server.broadcast("Server: "+ login +" left the server.");
    try {
        socket.close();
    }catch (IOException e){
        throw new RuntimeException(e);
    }
    }



}
