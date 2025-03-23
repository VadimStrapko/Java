package SkiRental;

public class Worker extends Thread{

    private Rental skiRental;
    private static int counter = 0;

    private int id;
    private boolean isFree;

    public void isFree(boolean b) {
        this.isFree = b;
    }

    public int getid() {
        return id;
    }

    public Worker(Rental skiRental) {
        this.skiRental = skiRental;
        this.id = counter++;
    }

    @Override
    public void run()  {
        while(true) {
            try {
                skiRental.rentSkis();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
