package service;

import java.util.Scanner;

public class CardPayment implements Payment {

    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    public CardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) throws Exception {

        if (amount <= 0) {
            throw new Exception("Invalid amount. Payment must be greater than 0.");
        }
        if (cardNumber == null || cardNumber.length() != 16) {
            throw new Exception("Invalid card number. Must be 16 digits.");
        }
        if (cvv == null || cvv.length() != 3) {
            throw new Exception("Invalid CVV. Must be 3 digits.");
        }

        //Simulate payment process
        System.out.println("Processing card payment of Rs." + amount + "...");
        Thread.sleep(2000); // simulate processing delay

        // For demo: approve if last digit of card is even
        if ((cardNumber.charAt(cardNumber.length() - 1) - '0') % 2 == 0) {
            System.out.println("Payment successful via Card. Amount: Rs." + amount);
        } else {
            throw new Exception("Payment failed. Card declined.");
        }
    }
}
