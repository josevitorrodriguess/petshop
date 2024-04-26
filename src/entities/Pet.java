package entities;

import java.io.Serializable;
import java.util.Objects;


public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;
    private int  id;
    private String name;
    private int age;
    private  String species;
    private String race;
    private Client owner;

    public Pet() {
    }
    public Pet(int id,String name, int age, String spicies, String race, Client owner) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.species = spicies;
        this.race = race;
        this.owner = owner;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String spicies) {
        this.species = spicies;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id && age == pet.age && Objects.equals(name, pet.name) && Objects.equals(species, pet.species) && Objects.equals(race, pet.race) && Objects.equals(owner, pet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, species, race, owner);
    }

    @Override
    public String toString() {
        return STR."Pet{id=\{id}, name='\{name}\{'\''}, age=\{age}, species='\{species}\{'\''}, race='\{race}\{'\''}, owner=\{owner}\{'}'}";
    }


}
