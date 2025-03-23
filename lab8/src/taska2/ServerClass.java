package taska2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;

public class ServerClass extends Thread {
    private PrintStream os;
    private BufferedReader is;
    private InetAddress addr;
    private Socket socket;
    public ServerClass(Socket s) throws IOException {
        os = new PrintStream(s.getOutputStream());
        is = new BufferedReader(new InputStreamReader(s.getInputStream()));
        addr = s.getInetAddress();
        socket = s;
    }
    public void run() {
        String str;
        try {
            while (!Objects.equals(str = is.readLine(), "disc")) {
                if (Integer.parseInt(str) == Main.number) {
                    os.write("Красава\n".getBytes());
                    System.out.println(addr.getHostName() + " угадал число " + Main.number);
                } else {
                    os.write("Не угадал\n".getBytes());
                }
                System.out.println(addr.getHostName() + ": " + str);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                disconnect();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void disconnect() throws IOException {
        try {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            System.out.println(addr.getHostName() + " всё");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            socket.close();
            this.interrupt();
        }
    }
}
