package dao.implement;

import dao.PaymentDAO;
import database.DB;
import database.exceptions.InsertErrorExeption;
import entities.Payment;

import java.sql.*;
import java.util.List;

public class PaymentDaoJDBC implements PaymentDAO {

    @Override
    public int insert(Payment obj) {
        String sql = "INSERT INTO payment (schedulingId,payment) VALUES (?, ?)";
        int idGerado = -1;


        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = DB.getConnection();
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1,obj.getSchedulingId());
            pstm.setBoolean(2,obj.isPayment());

            pstm.executeUpdate();

            try(ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()){
                    idGerado = rs.getInt(1);
                    obj.setId(idGerado);
                    System.out.println(STR."ID do pagamento: \{idGerado}");
                } else {
                    throw new SQLException("Não foi possível obter o ID gerado automaticamente.");
                }
            }
        } catch (SQLException e){
            throw new InsertErrorExeption(e.getMessage());
        } finally {
            try{
                if (pstm!=null){
                    pstm.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return idGerado;
    }

    @Override
    public void update(Payment obj) {
        String sql = "UPDATE payment SET n payment = ? WHERE id = ?" ;

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = DB.getConnection();
            pstm = connection.prepareStatement(sql);
        }

    }

    @Override
    public void deleteById(Payment obj) {

    }

    @Override
    public void findById(Payment obj) {

    }

    @Override
    public List<Payment> findAll() {
        return null;
    }
}
