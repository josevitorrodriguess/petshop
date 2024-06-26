package dao.implement;

import dao.ServiceDAO;
import database.exceptions.DeleteErrorException;
import database.exceptions.GetErrorException;
import database.exceptions.InsertErrorException;
import database.exceptions.UpdateErrorException;
import entities.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "UPDATE service SET name = COALESCE(?, name), description = COALESCE(?, description), price = COALESCE(?, price) WHERE id = ?";

        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(sql);

            if (obj.getName() != "") {
                pstm.setString(1, obj.getName());
            } else {
                pstm.setString(1,null);
            }

            if (obj.getDescription() != "") {
                pstm.setString(2, obj.getDescription());
            } else {
                pstm.setString(2,null);
            }

            if (obj.getPrice() != 0) {
                pstm.setDouble(3, obj.getPrice());
            } else {
                pstm.setObject(3,null);
            }
            pstm.setInt(4, obj.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateErrorException(e.getMessage());
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id, Connection connection) {
        String sql = "DELETE FROM service WHERE id=?";

        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,id);

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
        String sql = "SELECT * FROM service WHERE id=?";

        Service service = new Service();

        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,id);
            rset = pstm.executeQuery();

            if (rset.next()) {
                service.setId(rset.getInt("id"));
                service.setName(rset.getString("name"));
                service.setDescription(rset.getString("description"));
                service.setPrice(rset.getDouble("price"));
            }
        }catch (SQLException e){
            throw new GetErrorException(e.getMessage());
        }finally {
            try {
                if (pstm!=null){
                    pstm.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(service.toString()).append("\n");

        return result.toString();
        
    }

    @Override
    public List<Service> findAll(Connection connection) {
        String sql = "SELECT * FROM service";

        List<Service> allServices = new ArrayList<Service>();

        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            pstm = connection.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()){
                Service service = new Service();

                service.setId(rset.getInt("id"));
                service.setName(rset.getString("name"));
                service.setDescription(rset.getString("description"));
                service.setPrice(rset.getDouble("price"));

                allServices.add(service);
            }
        }catch (SQLException e){
            throw  new GetErrorException(e.getMessage());
        }finally {
            try {
                if (pstm!=null){
                    pstm.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return allServices;
    }
}
