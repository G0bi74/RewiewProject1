package pl.G0bi74.Client;

import java.util.Scanner;

public class Client{
    public void start() {
        ServerHandler serverHandler = new ServerHandler("localhost", 8020);
        Thread threadRead = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    String read = serverHandler.read();
                    if(read == null) break;
                    System.out.println(read);
                }
            }
        });
        threadRead.start();

        Thread threadWrite = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while(true) {
                    String message = scanner.nextLine();
                    serverHandler.send(message);
                    if(message.equalsIgnoreCase("bye")) {
                        serverHandler.close();
                        break;
                    }

                }
            }
        });
        threadWrite.start();

    }
}
