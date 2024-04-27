package dao.implement;

import dao.ClientDAO;
import database.DB;
import database.exceptions.InsertErrorExeption;
import entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CientDaoJDBC implements ClientDAO {

    @Override
    public int insert(Client obj) {
        String sql = "INSERT INTO client(name,address,number) VALUES (?,?,?)";
        int idGerado = -1;

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = DB.getConnection();
            pstm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1,obj.getName());
            pstm.setString(2,obj.getAddress());
            pstm.setString(3,obj.getPhoneNumber());

            pstm.executeUpdate();

            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                    System.out.println(STR."ID gerado: \{idGerado}");
                } else {
                    throw new SQLException("Não foi possível obter o ID gerado automaticamente.");
                }
            }
        } catch (SQLException e) {
            throw new InsertErrorExeption(e.getMessage());
        } finally {
           try{
               if (pstm!=null){
                   pstm.close();
               }
               if (connection!=null){
                   connection.close();
               }
           } catch (Exception e){
               e.printStackTrace();
           }
        }
        return idGerado;
    }

    @Override
    public void update(Client obj) {

    }

    @Override
    public void deleteById(Client obj) {

    }

    @Override
    public void findById(Client obj) {

    }

    @Override
    public List<Client> findAll() {
        return null;
    }
}
