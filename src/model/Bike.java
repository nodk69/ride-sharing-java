package model;

public class Bike extends Vehical {
    public Bike(String numberPlate, String model) {
        super(numberPlate, model);
    }

    @Override
    public double calculateFare(double distance) {
        return distance*10;
    }
}
