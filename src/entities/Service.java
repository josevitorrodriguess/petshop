package entities;

import java.io.Serializable;
import java.util.Objects;


public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private  String description;
    private  double price;

    public Service(){
    }

    public Service(int id,String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Service(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id && Double.compare(price, service.price) == 0 && Objects.equals(name, service.name) && Objects.equals(description, service.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }

    @Override
    public String toString() {
        return STR."Service{id=\{id}, name='\{name}\{'\''}, description='\{description}\{'\''}, price=\{price}\{'}'}";
    }
}
