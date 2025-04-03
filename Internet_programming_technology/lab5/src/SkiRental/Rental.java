package SkiRental;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Rental extends Thread {
    private volatile int numberOfSkis;
    private int maxWait;
    public static ConcurrentLinkedQueue<Client> queue = new ConcurrentLinkedQueue<Client>();

    public Queue<Client> getQueue() {
        return queue;
    }

    public void setQueue(ConcurrentLinkedQueue<Client> queue) {
        this.queue = queue;
    }

    public Rental(int numberOfSkis, int maxWait) {
        this.numberOfSkis = numberOfSkis;
        this.maxWait = maxWait;
    }

    public synchronized void servePensioner(Client pensioner) throws InterruptedException {
        if (numberOfSkis > 0) {
            numberOfSkis--;
            System.out.println("Пенсионер-клиент " + pensioner.getid() + " получил лыжи");
            wait(1000);
            pensioner.start();
        }
        else{
            try {
                wait(maxWait);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            if (numberOfSkis > 0) {
                numberOfSkis--;
                System.out.println("Пенсионер-клиент " + pensioner.getid() + " получил лыжи");
                wait(1000);
                pensioner.start();
            }
            else{
                System.out.println("Пенсионер-клиент " + pensioner.getid() + "ушёл");
            }
        }
    }

    public synchronized void rentSkis() throws InterruptedException {
        while(!queue.isEmpty() && numberOfSkis > 0)
        {
            for(Client client: queue) {
                if (client.isPensioner())
                {
                    queue.remove(client);
                    servePensioner(client);
                }
            }
           Client client = queue.poll();
            if(numberOfSkis > 0)
            {
                numberOfSkis--;
                client.setServed(true);
                System.out.println("Клиент " + client.getid() + " получил лыжи");
                wait(1000);
                client.start();
            }
            else
            {
                try {
                    wait(maxWait);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if(numberOfSkis > 0)
                {
                    numberOfSkis--;
                    client.setServed(true);
                    System.out.println("Клиент " + client.getid() + " получил лыжи");
                    wait(1000);
                    client.start();
                }
                else
                    System.out.println("Клиент " + client.getid() + " ушёл");
            }
            notifyAll();
        }
    }

    public synchronized void returnSkis(Client client)
    {
        numberOfSkis++;
        if(client.isPensioner())
        {
            System.out.println("Пенсионер-клиент " + client.getid() + " вернул лыжи");
        }
        else
            System.out.println("Клиент " + client.getid() + " вернул лыжи");
    }
}
