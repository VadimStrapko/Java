package Parking;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    private static final int NUMBER_OF_CARS = 15;
    private static final int FIRST_PARKING = 4;
    private static final int SECOND_PARKING = 5;

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Car> queue = new ArrayList<Car>();
        System.out.println("Cars");
        for (int i = 0; i < 10; i++) {
            queue.add(Car.getRandCar(i+1));
            System.out.println(queue.get(i));
        }
        System.out.println("Parking");
        Parking parking = new Parking(queue, FIRST_PARKING, SECOND_PARKING);
        try {
            parking.park();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (Car c : parking.queue) {
            c.join();
        }
    }

}