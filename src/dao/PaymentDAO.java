package dao;

import entities.Payment;

import java.sql.Connection;
import java.util.List;

public interface PaymentDAO {
    int insert(Payment obj, Connection connection);
    void update(Payment obj, Connection connection);
    void deleteById(Payment obj);
    void findById(Payment obj);
    List<Payment> findAll();
}
