package dao.implement;

import dao.ServiceDAO;
import database.exceptions.InsertErrorException;
import database.exceptions.UpdateErrorException;
import entities.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServiceDaoJDBC implements ServiceDAO {
    @Override
    public int insert(Service obj, Connection connection) {
        int idGerado = -1;
        String sql = "INSERT INTO service(name, description,price) VALUES(?,?,?)";

        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1,obj.getName());
            pstm.setString(2,obj.getDescription());
            pstm.setDouble(3,obj.getPrice());

            pstm.executeUpdate();

            try (ResultSet rs = pstm.getGeneratedKeys()){
                if (rs.next()){
                    idGerado = rs.getInt(1);
                    obj.setId(idGerado);
                    System.out.println(STR."ID do servico: \{idGerado}");
                } else {
                    throw new SQLException("Não foi possivel obter o ID gerado automaticamente.");
                }
               }
            } catch (SQLException e) {
            throw new InsertErrorException(e.getMessage());
        } finally {
            try{
                if (pstm!=null){
                    pstm.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return idGerado;
    }

    @Override
    public void update(Service obj, Connection connection) {
        String sql = "UPDATE service SET name =?, description =?, price =? WHERE id=?";

        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(sql);

            pstm.setString(1,obj.getName());
            pstm.setString(2,obj.getDescription());
            pstm.setDouble(3,obj.getPrice());
            pstm.setInt(4,obj.getId());

            pstm.executeUpdate();
        } catch (SQLException e){
            throw  new UpdateErrorException(e.getMessage());
        } finally {
            try{
                if (pstm!=null){
                    pstm.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Service obj, Connection connection) {
        String sql = "DELETE FROM service WHERE id=?";

        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,obj.getId());

            pstm.executeUpdate();
        }catch (SQLException e){
            throw  new UpdateErrorException(e.getMessage());
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
    public void findById(Service obj) {

    }

    @Override
    public List<Service> findAll() {
        return null;
    }
}
