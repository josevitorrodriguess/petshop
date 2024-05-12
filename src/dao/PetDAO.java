package dao;

import entities.Client;
import entities.Pet;

import java.sql.Connection;
import java.util.List;

public interface PetDAO {

    int insert(Pet obj, Connection connection);
    void update(Pet obj, Connection connection);
    void delete(int id, Connection connection);
    String get(Pet obj, Connection connection);
}
