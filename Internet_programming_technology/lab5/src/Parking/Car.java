package Parking;
public class Car extends Thread {
    public static Car getRandCar(int number) {
        return new Car((int) (Math.random() * 200 + 100), number);
    }
    public static Parking parking;
    public int number;
    public final int waitTime = 200;
    public int parkTime;
    public char parkingSite;
    public boolean isGone = false;
    public Car() {
        parkingSite = 'n';
    }
    public Car(int parkTime, int number) {
        this();
        this.parkTime = parkTime;
        this.number = number;
    }

    @Override
    public void run() {
        try {
            if (parkingSite == 'n') {
                if (parking.nSize + 1 <= parking.nMax) {
                    parking.nSize++;
                    System.out.println("Машина " + number + " стоит на парковке " + parkingSite);
                    Thread.sleep(parkTime);
                    isGone = true;
                } else {
                    Thread.sleep(parkTime);
                    if (parking.nSize + 1 <= parking.nMax) {
                        parking.nSize++;
                        System.out.println("Машина " + number + " стоит на парковке " + parkingSite);
                        Thread.sleep(parkTime);
                        isGone = true;
                    }
                    parkingSite = 'm';
                    System.out.println("Машина " + number + " поехала на стоянку m");
                }
            } else {
                if (parking.mSize + 1 <= parking.mMax) {
                    parking.mSize++;
                    System.out.println("Машина " + number + " стоит на парковке " + parkingSite);
                    Thread.sleep(parkTime);
                    isGone = true;
                } else {
                    Thread.sleep(parkTime);
                    if (parking.mSize + 1 <= parking.mMax) {
                        parking.mSize++;
                        System.out.println("Машина " + number + " стоит на парковке " + parkingSite);
                        Thread.sleep(parkTime);
                        isGone = true;
                    }
                    parkingSite = 'n';
                    System.out.println("Машина " + number + " поехала на стоянку n");
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        if (isGone) {
            System.out.println("Машина " + number + " уехала");
            if (parkingSite == 'n') {
                parking.nSize--;
            } else {
                parking.mSize--;
            }
        } else {
            run();
        }
    }

    @Override
    public String toString() {
        return "Машина " + number + " время:" + parkTime;
    }
}
