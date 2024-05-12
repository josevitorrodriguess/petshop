package dao;

import entities.Client;

import java.sql.Connection;
import java.util.List;

public interface ClientDAO {


    int insert(Client obj, Connection connection);
    void update(Client obj, Connection connection);
    void delete(int id,Connection connection);
    String get(int id, Connection connection);

}
