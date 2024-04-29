package dao.implement;

import dao.ServiceDAO;
import database.DB;
import database.exceptions.InsertErrorExeption;
import entities.Service;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServiceDaoJDBC implements ServiceDAO {
    @Override
    public int insert(Service obj) {
        int idGerado = -1;
        String sql = "INSERT INTO service(name, description,price) VALUES(?,?,?)";

        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = DB.getConnection();
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
            throw new InsertErrorExeption(e.getMessage());
//        } finally {
//            try{
//                if (pstm!=null){
//                    pstm.close();
//                }
//                if (connection!=null){
//                    connection.close();
//                }
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
        }
        return idGerado;
    }

    @Override
    public void update(Service obj) {

    }

    @Override
    public void deleteById(Service obj) {

    }

    @Override
    public void findById(Service obj) {

    }

    @Override
    public List<Service> findAll() {
        return null;
    }
}
