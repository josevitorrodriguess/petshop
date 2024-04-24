package entities;
import java.util.ArrayList;
import java.util.UUID;

public class Client {

    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private ArrayList<Pet> pets;

    public Client(String name, String endereco, String phoneNumber) {
        this.id = gerarIdAleatorio();
        this.name = name;
        this.address = endereco;
        this.phoneNumber = phoneNumber;
    }

    public Client(String name, String endereco, String phoneNumber, ArrayList<Pet> pets) {
        this.id = gerarIdAleatorio();
        this.name = name;
        this.address = endereco;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
    }

    @org.jetbrains.annotations.NotNull
    private String gerarIdAleatorio() {
        return UUID.randomUUID().toString().substring(0, 8);
    }


    public String getId() {
        return id;
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

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }
}
