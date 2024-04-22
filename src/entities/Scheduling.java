package entities;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class Scheduling {

    private String id;
    private String clientId;
    private String petId;
    private String serviceId;
    private LocalDateTime date;


    public Scheduling(String clientId, String petId, String serviceId, LocalDateTime date) {
        this.id = gerarIdAleatorio();
        this.clientId = clientId;
        this.petId = petId;
        this.serviceId = serviceId;
        this.date = date;
    }


    private @NotNull String gerarIdAleatorio() {
        return UUID.randomUUID().toString().substring(0, 8);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
