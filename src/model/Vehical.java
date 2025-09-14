package model;

public abstract class Vehical {

    protected String numberPlate;
    protected  String model;

    public Vehical(String numberPlate,String model){
        this.numberPlate= numberPlate;
        this.model = model;
    }

    public abstract double calculateFare(double distance);
}
