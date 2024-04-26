package dao;

import entities.Client;

import java.util.List;

public interface ClientDAO {

    void insert(Client obj);
    void update(Client obj);
    void deleteById(Client obj);
    void findById(Client obj);
    List<Client>findAll();
}
