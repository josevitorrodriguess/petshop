package dao;

import entities.Pet;
import entities.Service;

import java.sql.Connection;
import java.util.List;

public interface ServiceDAO {

    int insert(Service obj, Connection connection);
    void update(Service obj, Connection connection);
    void delete(Service obj, Connection connection);
    String get(Service obj, Connection connection);
    List<Service> findAll();
}
