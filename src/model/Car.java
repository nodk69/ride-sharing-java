package model;

public class Car extends Vehical {

    public Car(String numberPlate, String model) {
        super(numberPlate, model);
    }

    @Override
    public double calculateFare(double distance) {
        return distance*10;
    }
}
