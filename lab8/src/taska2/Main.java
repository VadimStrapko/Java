package taska2;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Main {
    public static int number;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8030);
            System.out.println("Сервер запущен");
            Random random = new Random();
            number = (int) Math.floor(random.nextDouble() * 100);
            System.out.println("Загадано " + number);
            while (true) {
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress().getHostName() + " подключился");
                ServerClass serverClass = new ServerClass(socket);
                serverClass.start();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}