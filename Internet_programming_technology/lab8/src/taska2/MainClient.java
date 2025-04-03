package taska2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) throws IOException {
        Socket socketClient = new Socket("0.0.0.0", 8030);
        BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        OutputStream out = socketClient.getOutputStream();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine() + "\n";
            out.write(str.getBytes());
            System.out.println(in.readLine());
            if (str.equals("disc\n")) {
                return;
            }
        }
    }
}