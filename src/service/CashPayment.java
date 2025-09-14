package service;

public class CashPayment implements Payment {
    @Override
    public void pay(double amount) throws Exception {
        System.out.println("Paid Rs." + amount + " in Cash");
    }
}
