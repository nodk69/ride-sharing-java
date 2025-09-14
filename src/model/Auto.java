package model;

public class Auto extends Vehical {
    public Auto(String numberPlate, String model) {
        super(numberPlate, model);
    }

    @Override
    public double calculateFare(double distance) {
        return distance*10;
    }
}
