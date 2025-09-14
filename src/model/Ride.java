package model;

public class Ride {

    private Passenger passenger;
    private Driver driver;
    private double distance;
    private double fare;
    private RideStatus status;

    public Ride (Passenger passenger,Driver driver,double distance){
        this.passenger= passenger;
        this.driver = driver;
        this.distance = distance;
        this.fare = driver.getVehical().calculateFare(distance);
        this.status = RideStatus.REQUESTED;
    }

    public void startRide(){
        this.status = RideStatus.ACCEPTED;
    }

    public void endRide(){
        this.status= RideStatus.COMPLETED;
    }

    public double getFare(){
        return fare;
    }

    public double getDistance(){
        return distance;
    }

    public Passenger getPassenger(){
        return passenger;
    }

    public RideStatus getStatus(){
        return status;
    }

    public Driver getDriver(){
        return driver;
    }
}

