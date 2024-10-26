package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void runServer() {
        try {
            while (!serverSocket.isClosed()) {

                Socket socket = serverSocket.accept(); // переводит основной поток приложения в ожидание подключения нового сокета
                ClientManager clientManager = new ClientManager(socket); // при подключении клиентского сокета сразу создем объект

                System.out.println("Подключен новый клиент!");

                Thread thread = new Thread(clientManager); // и создаем отдельный поток
                thread.start(); // запускаем его и далее в рамках итерации цикла переходим в режим ожидания .accept()
            }
        } catch (IOException e) {
            closeSocket(); // завершение работы клиентского сокета
        }
    }

    private void closeSocket() {
        try {
            if(serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
