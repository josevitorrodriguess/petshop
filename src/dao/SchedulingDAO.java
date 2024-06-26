package dao;

import entities.Pet;
import entities.Scheduling;

import java.sql.Connection;
import java.util.List;

public interface SchedulingDAO {

    int[] insert(Scheduling obj, Connection connection);
    void update(Scheduling obj, Connection connection);
    void delete(int id, Connection connection);
    String get(int id, Connection connection);
}
