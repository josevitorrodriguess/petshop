package dao;

import entities.Client;

import java.sql.Connection;
import java.util.List;

public interface ClientDAO {


    int insert(Client obj, Connection connection);
    void update(Client obj, Connection connection);
    void deleteById(Client obj);
    void findById(Client obj);
    List<Client>findAll();
}