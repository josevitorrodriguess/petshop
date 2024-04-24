package entities;

import java.util.UUID;

public class Pet {


    private String  id;
    private String name;
    private String age;
    private  String species;
    private String race;


    public Pet(String name, String age, String spicies, String race) {
        this.id = gerarIdAleatorio();
        this.name = name;
        this.age = age;
        this.species = spicies;
        this.race = race;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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
}
