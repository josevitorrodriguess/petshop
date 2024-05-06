package dao;

import entities.Payment;

import java.sql.Connection;
import java.util.List;

public interface PaymentDAO {
    int insert(Payment obj, Connection connection);
    void update(Payment obj, Connection connection);
    void delete(Payment obj, Connection connection);
    String get(Payment obj, Connection connection);
    List<Payment> findAll();
}
