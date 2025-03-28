package task3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class SendUDP {

    private DatagramSocket socket;

    public SendUDP(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void sendMessages(String messages, int port) throws IOException {
        DatagramPacket message = new DatagramPacket(messages.getBytes(), messages.getBytes().length, InetAddress.getLocalHost(), port);
        socket.send(message);
    }

    public static void main(String[] args) throws IOException {
        SendUDP udp = new SendUDP(1234);
        udp.sendMessages("Hello, udp", 4321);
    }
}
