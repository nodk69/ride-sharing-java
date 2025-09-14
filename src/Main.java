import model.*;
import service.*;
import exception.NoDriverAvailableException;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RideService rideService = new RideService();

        while (true) {
            System.out.println("\n=== Ride Sharing App ===");
            System.out.println("1. Register Driver");
            System.out.println("2. Register Passenger");
            System.out.println("3. Book Ride");
            System.out.println("4. Show Passenger Ride History");
            System.out.println("5. Show All Rides Sorted by Fare");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1: // Register Driver
                    System.out.print("Enter Driver ID: ");
                    int did = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Driver Name: ");
                    String dname = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String dphone = sc.nextLine();
                    System.out.println("Choose Vehicle Type: 1.Car  2.Bike  3.Auto");
                    int vtype = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Vehicle Number: ");
                    String vnum = sc.nextLine();
                    System.out.print("Enter Vehicle Model: ");
                    String vmodel = sc.nextLine();

                    Vehical vehicle;
                    switch (vtype) {
                        case 1: vehicle = new Car(vnum, vmodel); break;
                        case 2: vehicle = new Bike(vnum, vmodel); break;
                        case 3: vehicle = new Auto(vnum, vmodel); break;
                        default: System.out.println("Invalid vehicle type!"); continue;
                    }

                    rideService.registerDriver(new Driver(did, dname, dphone, vehicle));
                    System.out.println("Driver registered successfully!");
                    break;

                case 2: // Register Passenger
                    System.out.print("Enter Passenger ID: ");
                    int pid = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Passenger Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String pphone = sc.nextLine();

                    rideService.registerPassenger(new Passenger(pid, pname, pphone));
                    System.out.println("Passenger registered successfully!");
                    break;

                case 3: // Book Ride
                    if (rideService.getPassengers().isEmpty() || rideService.getDrivers().isEmpty()) {
                        System.out.println("⚠ Please register at least 1 driver and 1 passenger first!");
                        break;
                    }

                    System.out.println("Select Passenger:");
                    for (Passenger p : rideService.getPassengers()) {
                        System.out.println(p.getId() + ". " + p.getName());
                    }
                    int bookPid = sc.nextInt(); sc.nextLine();
                    Passenger selectedPassenger = null;
                    for (Passenger p : rideService.getPassengers()) {
                        if (p.getId() == bookPid) selectedPassenger = p;
                    }
                    if (selectedPassenger == null) {
                        System.out.println("Invalid Passenger ID!");
                        break;
                    }

                    System.out.print("Enter ride distance (km): ");
                    double distance = sc.nextDouble(); sc.nextLine();

                    try {
                        rideService.bookRide(selectedPassenger, distance);
                        System.out.println("Ride booked successfully!");

                        List<Ride> rides = rideService.getPassengerRideHistory()
                                .getOrDefault(selectedPassenger, new ArrayList<>());

                        if (rides.isEmpty()) {
                            System.out.println("⚠ No ride found in history for this passenger.");
                            break;
                        }

                        Ride lastRide = rides.get(rides.size() - 1);

                        double fare = lastRide.getFare();
                        System.out.println("Ride Fare: Rs." + fare);
                        System.out.println("Choose Payment Method: 1.Cash  2.Card  3.Wallet");
                        int payChoice = sc.nextInt(); sc.nextLine();

                        Payment payment = null;
                        switch (payChoice) {
                            case 1:
                                payment = new CashPayment();
                                break;
                            case 2:
                                System.out.print("Enter Card Number (16 digits): ");
                                String cardNum = sc.nextLine();
                                System.out.print("Enter Card Holder Name: ");
                                String holder = sc.nextLine();
                                System.out.print("Enter Expiry Date (MM/YY): ");
                                String expiry = sc.nextLine();
                                System.out.print("Enter CVV: ");
                                String cvv = sc.nextLine();
                                payment = new CardPayment(cardNum, holder, expiry, cvv);
                                break;
                            case 3:
                                payment = new WalletPayment(1000); // Rs.1000 balance
                                break;
                            default:
                                System.out.println("Invalid payment choice.");
                        }

                        if (payment != null) {
                            try {
                                payment.pay(fare);
                            } catch (Exception e) {
                                System.out.println("Payment Failed: " + e.getMessage());
                            }
                        }

                    } catch (NoDriverAvailableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4: // Show Passenger History
                    System.out.print("Enter Passenger ID: ");
                    int histPid = sc.nextInt(); sc.nextLine();
                    Passenger histPassenger = null;
                    for (Passenger p : rideService.getPassengers()) {
                        if (p.getId() == histPid) histPassenger = p;
                    }
                    if (histPassenger != null) {
                        rideService.showPassengerHistory(histPassenger);
                    } else {
                        System.out.println("Invalid Passenger ID!");
                    }
                    break;

                case 5:
                    rideService.showSortedHistoryByFare();
                    break;

                case 6:
                    System.out.println("Exiting Ride Sharing App...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
