package model;

public class Driver extends User {

    private Vehical vehical;
    private boolean available;

    public Driver(int id, String name, String phone,Vehical vehical) {
        super(id, name, phone);
        this.vehical = vehical;
        this.available = true;
    }

    public boolean isAvailable(){
        return available;
    }

    public void setAvailable(boolean available){
        this.available= available;
    }

    public Vehical getVehical(){
        return vehical;
    }
}
