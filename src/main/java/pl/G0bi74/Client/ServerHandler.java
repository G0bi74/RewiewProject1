package pl.G0bi74.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerHandler {
    String hostName;
    Socket socket;
    int port;
    Scanner input;
    PrintWriter output;

    public ServerHandler(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;

        try {
            socket = new Socket(hostName, port);
            input  = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void send(String message){output.println(message);}
    public String read(){ return input.hasNextLine() ?  input.nextLine() : null;}
    public void close(){ try{ socket.close(); }catch (IOException e){ throw new RuntimeException(e);}}

    public String readLineFromCSV(String filePath){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            return br.readLine();
        }catch (IOException e){ throw new RuntimeException(e);}
    }

    public void sendData(String name, String filePath){
        String line;
        send(name);
        do {
            line = readLineFromCSV(filePath);
            send(line);
        }while (input.hasNextLine());
    }
}
