package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Program {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1400);
            Server server = new Server(serverSocket);
            server.runServer();

        } catch (UnknownHostException e) { // например хост отсутствует
            e.printStackTrace();
        } catch (IOException e) { // возникает в процессе создания сокета
            e.printStackTrace();
        }
    }
}
