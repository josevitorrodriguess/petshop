package dao.implement;

import dao.PaymentDAO;
import database.DB;
import database.exceptions.InsertErrorExeption;
import database.exceptions.UpdateErrorExeption;
import entities.Payment;

import java.sql.*;
import java.util.List;

public class PaymentDaoJDBC implements PaymentDAO {

    @Override
    public int insert(Payment obj, Connection connection) {
        String sql = "INSERT INTO payment (schedulingId,payment) VALUES (?, ?)";
        int idGerado = -1;


        PreparedStatement pstm = null;

        try {
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
                if (pstm!=null) {
                    pstm.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return idGerado;
    }

    @Override
    public void update(Payment obj,Connection connection) {
        String sql = "UPDATE payment SET payment = ? WHERE id = ?" ;

        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(sql);

            pstm.setBoolean(1,obj.isPayment());

            pstm.setInt(2,obj.getId());

            pstm.executeUpdate();
        } catch (SQLException e){
            throw new UpdateErrorExeption(e.getMessage());
        } finally {
            try {
                if (pstm!=null){
                    pstm.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(Payment obj, Connection connection) {
        String sql = "DELETE FROM payment WHERE id=?";

        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,obj.getId());

            pstm.executeUpdate();
        }catch (SQLException e){
            throw  new UpdateErrorExeption(e.getMessage());
        }finally {
            try {
                if (pstm!=null){
                    pstm.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void findById(Payment obj) {

    }

    @Override
    public List<Payment> findAll() {
        return null;
    }
}
