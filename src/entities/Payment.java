package entities;

public class Payment {
    private  String idScheduling;
    private boolean payment = false;


    public Payment(String idScheduling, boolean payment) {
        this.idScheduling = idScheduling;
        this.payment = payment;
    }

    public String getIdScheduling() {
        return idScheduling;
    }

    public void setIdScheduling(String idScheduling) {
        this.idScheduling = idScheduling;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }
}
