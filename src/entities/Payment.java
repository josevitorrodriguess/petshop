package entities;

import java.io.Serializable;
import java.util.Objects;


public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    private  int id;
    private  int schedulingId;
    private boolean payment;

    public Payment(){
    }

    public Payment(int schedulingId, boolean payment) {
        this.schedulingId = schedulingId;
        this.payment = payment;
    }
    public Payment(int id,int schedulingId) {
        this.id = id;
        this.schedulingId = schedulingId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchedulingId() {
        return schedulingId;
    }

    public void setSchedulingId(int schedulingId) {
        this.schedulingId = schedulingId;
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
        return id == payment1.id && schedulingId == payment1.schedulingId && payment == payment1.payment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schedulingId, payment);
    }

    @Override
    public String toString() {
        return STR."Payment{id=\{id}, schedulingId=\{schedulingId}, payment=\{payment}\{'}'}";
    }
}
