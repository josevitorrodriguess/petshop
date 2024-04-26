package dao;

import java.util.List;

public interface PaymentDAO {
    void insert(PaymentDAO obj);
    void update(PaymentDAO obj);
    void deleteById(entities.Payment obj);
    void findById(PaymentDAO obj);
    List<PaymentDAO> findAll();
}
