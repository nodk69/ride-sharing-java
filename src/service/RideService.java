package service;

import exception.NoDriverAvailableException;
import model.Driver;
import model.Passenger;
import model.Ride;

import java.util.*;

public class RideService {

    //Make the nessacry database that we need
    private List<Driver> drivers = new ArrayList<>();
    private List<Passenger> passengers = new ArrayList<>();
    private  List<Ride> rideHistory = new ArrayList<>();
    private Queue<Ride> rideRequest = new LinkedList<>();
    private Map<Passenger,List<Ride>> passengerRideHistory = new HashMap<>();

    //now create  a driver
    public void registerDriver(Driver driver){
        drivers.add(driver);
    }

    //create a passenger
    public void registerPassenger(Passenger passenger){
        passengers.add(passenger);
    }

    public void bookRide(Passenger passenger,double distance) throws NoDriverAvailableException{
        for(Driver d : drivers){
            if(d.isAvailable()){
                Ride ride = new Ride(passenger,d,distance);
                rideRequest.add(ride);
                passengerRideHistory.putIfAbsent(passenger,new ArrayList<>());
                passengerRideHistory.get(passenger).add(ride);
                assignDriver();
                return;
            }
        }
        throw new NoDriverAvailableException("No driver is available right now!");
    }

    //import method
    private void assignDriver(){
        if(!rideRequest.isEmpty()){
            Ride ride = rideRequest.poll();
            ride.startRide();

            //simulate ride end in next thread
            new Thread(()->{
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                ride.endRide();
                rideHistory.add(ride);
                passengerRideHistory.get(ride.getPassenger()).add(ride);
                System.out.println("Ride completed for " + ride.getPassenger().getName() +
                        " | Fare: Rs." + ride.getFare());
            }).start();
        }
    }

    public void showPassengerHistory(Passenger passenger){
        System.out.println("History for "+ passenger.getName());
        List<Ride> rides = passengerRideHistory.getOrDefault(passenger,new ArrayList<>());
        rides.forEach(r-> System.out.println(" - " + r.getDistance() + "km | Fare: "+ r.getFare()));
    }

    public void showSortedHistoryByFare(){
        rideHistory.sort(Comparator.comparingDouble(Ride::getFare));
        System.out.println("All rides sorted by fare:");
        rideHistory.forEach(r -> System.out.println("Passenger: " + r.getPassenger().getName() + " | Fare: " + r.getFare()));
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Driver> getDrivers(){
        return drivers;
    }

    public Map<Passenger, List<Ride>> getPassengerRideHistory() {
        return passengerRideHistory;
    }

}
