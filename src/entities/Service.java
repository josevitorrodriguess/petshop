package entities;

import java.util.UUID;

public class Service {
    private String id;
    private String name;
    private  String description;
    private  double price;

    public Service(String name, String description, double price) {
        this.id = gerarIdAleatorio();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    @org.jetbrains.annotations.NotNull
    private String gerarIdAleatorio() {
        return UUID.randomUUID().toString().substring(0, 8);
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
