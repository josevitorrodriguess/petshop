package dao;

import entities.Pet;
import entities.Scheduling;

import java.util.List;

public interface SchedulingDAO {
    void insert(Scheduling obj);
    void update(Scheduling obj);
    void deleteById(Scheduling obj);
    void findById(Scheduling obj);
    List<Scheduling> findAll();
}
