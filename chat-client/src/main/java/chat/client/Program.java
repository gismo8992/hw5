package chat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите свое имя: ");

            String name = scanner.nextLine();
            Socket socket = new Socket("localhost", 1400); // ip адрес машины к которой подключаемся и порт, на которой машина слушает
            Client client = new Client(socket, name);
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("InetAddress: " + inetAddress);
            String remoteIp = inetAddress.getHostAddress();
            System.out.println("Remote IP: " + remoteIp);
            System.out.println("LocalPort: " + socket.getLocalPort());

            client.listenForMessage();
            client.sendMessage();

        } catch (UnknownHostException e) { // например хост отсутствует
            e.printStackTrace();
        } catch (IOException e) { // возникает в процессе создания сокета
            e.printStackTrace();
        }
    }
}
