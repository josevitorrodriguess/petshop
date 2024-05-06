package dao;

import entities.Pet;
import entities.Scheduling;

import java.sql.Connection;
import java.util.List;

public interface SchedulingDAO {

    int[] insert(Scheduling obj, Connection connection);
    void update(Scheduling obj, Connection connection);
    void delete(Scheduling obj, Connection connection);
    String get(Scheduling obj, Connection connection);
    List<Scheduling> findAll();
}
