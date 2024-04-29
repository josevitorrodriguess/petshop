package dao;

import entities.Pet;
import entities.Service;

import java.util.List;

public interface ServiceDAO {

    int insert(Service obj);
    void update(Service obj);
    void deleteById(Service obj);
    void findById(Service obj);
    List<Service> findAll();
}
