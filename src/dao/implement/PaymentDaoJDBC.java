package dao.implement;

import dao.PaymentDAO;
import database.exceptions.DeleteErrorException;
import database.exceptions.GetErrorException;
import database.exceptions.InsertErrorException;
import database.exceptions.UpdateErrorException;
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
            throw new InsertErrorException(e.getMessage());
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
            throw new UpdateErrorException(e.getMessage());
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
            throw  new DeleteErrorException(e.getMessage());
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
    public String get(int id, Connection connection) {
        String sql = "SELECT * FROM payment WHERE id=?";
        Payment payment = new Payment();

        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();


            if (rset.next()) {
                payment.setId(rset.getInt("id"));
                payment.setSchedulingId(rset.getInt("schedulingId"));
                payment.setPayment(rset.getBoolean("payment"));
            } else {
                throw new SQLException("Nenhum resultado encontrado para o ID fornecido.");
            }
        } catch (SQLException e) {
            throw new GetErrorException(e.getMessage());
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Payment Details: \n");
        resultBuilder.append("ID: ").append(payment.getId()).append("\n");
        resultBuilder.append("Scheduling ID: ").append(payment.getSchedulingId()).append("\n");
        resultBuilder.append("Payment Status: ").append(payment.isPayment() ? "Pago" : "Não Pago").append("\n");

        return resultBuilder.toString();
    }
}
