package dao.implement;

import dao.PetDAO;
import database.DB;
import database.exceptions.InsertErrorExeption;
import entities.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class PetDaoJDBC implements PetDAO {
    @Override
    public int insert(Pet obj) {
        String sql = "INSERT INTO pet(name,age,species,race,clientId) VALUES (?,?,?,?,?)";
        int idGerado = -1;

        Connection connection = null;
        PreparedStatement pstm = null;


        try {
            connection = DB.getConnection();
            pstm = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1,obj.getName());
            pstm.setInt(2,obj.getAge());
            pstm.setString(3,obj.getSpecies());
            pstm.setString(4,obj.getRace());
            pstm.setInt(5,obj.getClientId());

            pstm.executeUpdate();

            try(ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()){
                    idGerado = rs.getInt(1);
                    System.out.println(STR."ID gerado: \{idGerado}");
                }else {
                    throw new SQLException("Não foi possível obter o ID gerado automáticamente.");
                }
            }
        } catch (SQLException e){
            throw new InsertErrorExeption(e.getMessage());
        }finally {
            try {
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
    public void update(Pet obj) {

    }

    @Override
    public void deleteById(Pet obj) {

    }

    @Override
    public void findById(Pet obj) {

    }

    @Override
    public List<Pet> findAll() {
        return null;
    }
}
