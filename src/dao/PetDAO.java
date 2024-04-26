package dao;

import entities.Client;
import entities.Pet;

import java.util.List;

public interface PetDAO {
    void insert(Pet obj);
    void update(Pet obj);
    void deleteById(Pet obj);
    void findById(Pet obj);
    List<Pet> findAll();
}
