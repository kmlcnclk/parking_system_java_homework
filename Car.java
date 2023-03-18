import java.util.Date;

public class Car {

    private String type;
    private Date arrivalTime;
    private int parkSpace;
    // private double totalPrice;

    public Car(String type, Date arrivalTime, int parkSpace) {
        this.type = type;
        this.arrivalTime = arrivalTime;
        this.parkSpace = parkSpace;
    }

    public String getType() {
        return type;
    }

    public int getParkSpace() {
        return parkSpace;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    // public double getTotalPrice() {
    // return totalPrice;
    // }

    // public void setTotalPrice() {
    // Date now = new Date();
    // this.totalPrice = ((arrivalDate.getTime() - now.getTime()) / 1000 / 60 / 60);
    // }

}
