package entities;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


public class Client implements Serializable  {


    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String address;
    private String phoneNumber;

    public Client() {
    }

    public Client(int id,String name, String endereco, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = endereco;
        this.phoneNumber = phoneNumber;
    }

    public Client(String name, String endereco, String phoneNumber) {
        this.name = name;
        this.address = endereco;
        this.phoneNumber = phoneNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String endereco) {
        this.address = endereco;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(name, client.name) && Objects.equals(address, client.address) && Objects.equals(phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phoneNumber);
    }

    @Override
    public String toString() {
        return STR."Client{id=\{id}, name='\{name}\{'\''}, address='\{address}\{'\''}, phoneNumber='\{phoneNumber}\{'\''}\{'}'}";
    }
}
