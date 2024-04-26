package entities;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.Objects;


public class Scheduling implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private LocalDateTime date;
    private Client client;
    private Pet pet;
    private Service service;


    public Scheduling(){

    }

    public Scheduling(int id, LocalDateTime date, Client client, Pet pet, Service service) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.pet = pet;
        this.service = service;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scheduling that = (Scheduling) o;
        return id == that.id && Objects.equals(date, that.date) && Objects.equals(client, that.client) && Objects.equals(pet, that.pet) && Objects.equals(service, that.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, client, pet, service);
    }

    @Override
    public String toString() {
        return STR."Scheduling{id=\{id}, date=\{date}, client=\{client}, pet=\{pet}, service=\{service}\{'}'}";
    }
}
