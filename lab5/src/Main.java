import SkiRental.Client;
import SkiRental.Rental;
import SkiRental.Worker;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numberOfWorkers = 2;
        int numberOfClients = 20;
        ConcurrentLinkedQueue<Client> queue = new ConcurrentLinkedQueue<Client>();
        Rental skiRental = new Rental(2, 1000);
        for(int i = 0; i < numberOfClients; i++)
        {
            queue.add(new Client(skiRental));
        }
        skiRental.setQueue(queue);
        for (int i = 0; i < numberOfWorkers; i++) {
            Worker worker = new Worker(skiRental);
            worker.start();
        }
    }
}