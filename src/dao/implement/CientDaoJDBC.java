package dao.implement;

import dao.ClientDAO;
import database.DB;
import database.exceptions.InsertErrorExeption;
import database.exceptions.UpdateErrorExeption;
import entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CientDaoJDBC implements ClientDAO {



    @Override
    public int insert(Client obj, Connection connection) {
        String sql = "INSERT INTO client(name,address,number) VALUES (?,?,?)";
        int idGerado = -1;


        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1,obj.getName());
            pstm.setString(2,obj.getAddress());
            pstm.setString(3,obj.getPhoneNumber());

            pstm.executeUpdate();

            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                    obj.setId(idGerado);
                    System.out.println(STR."ID do cliente: \{idGerado}");
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
           }  catch (SQLException e){
               e.printStackTrace();
           }
        }
        return idGerado;
    }

    @Override
    public void update(Client obj, Connection connection) {
        String sql = "UPDATE client SET name = ?, address = ?, number = ? WHERE id =?";

        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(sql);

            pstm.setString(1,obj.getName());
            pstm.setString(2,obj.getAddress());
            pstm.setString(3,obj.getPhoneNumber());

            pstm.setInt(4,obj.getId());

            pstm.executeUpdate();
        } catch (SQLException e){
            throw new UpdateErrorExeption(e.getMessage());
        }   finally {
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
    public void delete(Client obj, Connection connection) {
        String sql = "DELETE FROM client WHERE id=?";

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
    public void findById(Client obj) {

    }

    @Override
    public List<Client> findAll() {
        return null;
    }
}
