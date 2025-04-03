package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Connect {

    public void getLocalHost() throws UnknownHostException {
        try {
            System.out.println("Localhost IP: " + InetAddress.getLocalHost().getHostAddress());
        }
        catch (UnknownHostException ex)
        {
            ex.getStackTrace();
        }
    }

    public void getByName(String website) {
        try{
            InetAddress websiteId = InetAddress.getByName(website);
            System.out.println("IP по названию: " + websiteId.getHostAddress());
        }
        catch(UnknownHostException ex)
        {
        ex.getStackTrace();
        }
    }

    public void getByAdress(String name, byte[] ip)
    {
        try{
            InetAddress pageIp = InetAddress.getByAddress(name, ip);
            System.out.println("IP доступен? " + pageIp.isReachable(300));
        }
        catch(IOException ex)
        {
            ex.getStackTrace();
        }
    }

    public void readHTML(String urlName) {
        try {       // еще можно весь штмл считать через команды

            // в url закидываем адрес сайта
            var url = new URL(urlName);
            // через буферизированный поток все читаем построчно
            try (var reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String HTMLLine;
                while ((HTMLLine = reader.readLine()) != null) {
                    System.out.println(HTMLLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}
