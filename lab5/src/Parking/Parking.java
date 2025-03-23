package Parking;

import java.io.Serializable;
import java.util.ArrayList;

public class Parking implements Serializable {
    public final int nMax;
    public volatile int nSize;
    public final int mMax;
    public volatile int mSize;
    public ArrayList<Car> queue;
    public Parking(ArrayList<Car> list, int n, int m) {
        this.queue = list;
        this.nMax = n;
        this.mMax = m;
        this.nSize = 0;
        this.mSize = 0;
    }
    public void park() throws InterruptedException {
        Car.parking = this;
        for (Car c : queue) {
            c.start();
        }
    }
}
