package SkiRental;

public class Client extends Thread {

    private Rental skiRental;
    private static int counter;
    private int id;
    private boolean isPensioner;

    private boolean isServed;

    private boolean hasSkis = false;

    public boolean isHasSkis() {
        return hasSkis;
    }

    public void setHasSkis(boolean hasSkis) {
        this.hasSkis = hasSkis;
    }

    public boolean isServed() {
        return isServed;
    }

    public void setServed(boolean served) {
        isServed = served;
    }

    public boolean isPensioner() {
        return isPensioner;
    }

    public Client(Rental skiRental) {
        this.id = counter++;
        if(id % 5 == 0)
            this.isPensioner = true;
            else
                this.isPensioner = false;
            this.skiRental = skiRental;
    }

    public int getid() {
        return id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        skiRental.returnSkis(this);
    }
}
