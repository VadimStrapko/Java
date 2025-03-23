package task1;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        Connect connect = new Connect();
        connect.getLocalHost();
        connect.getByName("www.twitch.tv");
        byte[] ip1 = {(byte)151, (byte)101, (byte)242, (byte)167};
        connect.getByAdress("Twitch", ip1);
        connect.readHTML("https://www.twitch.tv/");
    }
}