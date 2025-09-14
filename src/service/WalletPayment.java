package service;

public class WalletPayment implements Payment {
    private double walletBalance;

    public WalletPayment(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    @Override
    public void pay(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Invalid amount. Payment must be greater than 0.");
        }
        if (amount > walletBalance) {
            throw new Exception("Wallet balance too low! Current balance: Rs." + walletBalance);
        }

        //processing delay
        System.out.println("Processing wallet payment of Rs." + amount + "...");
        Thread.sleep(1500);

        walletBalance -= amount;
        System.out.println(" Paid Rs." + amount + " via Wallet. Remaining Balance: Rs." + walletBalance);
    }

    public double getWalletBalance() {
        return walletBalance;
    }
}
