package dao;

import entities.Payment;

import java.util.List;

public interface PaymentDAO {
    int insert(Payment obj);
    void update(Payment obj);
    void deleteById(Payment obj);
    void findById(Payment obj);
    List<Payment> findAll();
}
