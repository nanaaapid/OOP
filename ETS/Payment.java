public class Payment {
    private int paymentAmount;

    public Payment(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public boolean isEnough(int totalPrice) {
        return paymentAmount >= totalPrice;
    }

    public int getChange(int totalPrice) {
        return paymentAmount - totalPrice;
    }
}
