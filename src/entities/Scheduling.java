package entities;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.Objects;


public class Scheduling implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private LocalDateTime date;
    private int clientId;
    private int petId;
    private int serviceId;


    public Scheduling(){
    }

    public Scheduling(LocalDateTime date, int clientId, int petId, int serviceId) {
        this.date = date;
        this.clientId = clientId;
        this.petId = petId;
        this.serviceId = serviceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scheduling that = (Scheduling) o;
        return id == that.id && clientId == that.clientId && petId == that.petId && serviceId == that.serviceId && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, clientId, petId, serviceId);
    }

    @Override
    public String toString() {
        return STR."Scheduling{id=\{id}, date=\{date}, clientId=\{clientId}, petId=\{petId}, serviceId=\{serviceId}\{'}'}";
    }
}
