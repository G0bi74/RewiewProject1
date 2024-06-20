package pl.G0bi74.Server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(8020);
    }
}