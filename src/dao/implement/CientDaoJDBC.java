package dao.implement;

import dao.ClientDAO;
import database.DB;
import database.exceptions.DbExeption;
import database.exceptions.InsertErrorExeption;
import entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CientDaoJDBC implements ClientDAO {

    @Override
    public void insert(Client obj) {
        String sql = "INSERT INTO client(name,address,number) VALUES (?,?,?)";

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = DB.getConnection();
            pstm = connection.prepareStatement(sql);

            pstm.setString(1,obj.getName());
            pstm.setString(2,obj.getAddress());
            pstm.setString(3,obj.getPhoneNumber());

            pstm.execute();
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
