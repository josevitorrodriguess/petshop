package entities;

import java.io.Serializable;
import java.util.Objects;


public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    private  int id;
    private  Scheduling scheduling;
    private boolean payment = false;

    public Payment(){

    }
    public Payment(int id,Scheduling scheduling) {
        this.id =id;
        this.scheduling = scheduling;
        this.payment = false;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Scheduling getScheduling() {
        return scheduling;
    }

    public void setScheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment1 = (Payment) o;
        return id == payment1.id && payment == payment1.payment && Objects.equals(scheduling, payment1.scheduling);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scheduling, payment);
    }

    @Override
    public String toString() {
        return STR."Payment{id=\{id}, scheduling=\{scheduling}, payment=\{payment}\{'}'}";
    }
}
