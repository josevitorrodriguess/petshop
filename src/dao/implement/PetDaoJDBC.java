package dao.implement;

import dao.PetDAO;
import database.DB;
import database.exceptions.InsertErrorExeption;
import database.exceptions.UpdateErrorExeption;
import entities.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class PetDaoJDBC implements PetDAO {
    @Override
    public int insert(Pet obj, Connection connection) {
        String sql = "INSERT INTO pet(name,age,species,race,clientId) VALUES (?,?,?,?,?)";
        int idGerado = -1;


        PreparedStatement pstm = null;


        try {
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
                    obj.setId(idGerado);
                    System.out.println(STR."ID do pet: \{idGerado}");
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
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return idGerado;
    }

    @Override
    public void update(Pet obj, Connection connection) {
        String sql = "UPDATE pet SET  name = ?, age = ?, species = ?, race = ?, clientId =?"+
                     " WHERE id = ?";

        PreparedStatement pstm = null;

        try {
           pstm = connection.prepareStatement(sql);

           pstm.setString(1,obj.getName());
           pstm.setInt(2,obj.getAge());
           pstm.setString(3, obj.getSpecies());
           pstm.setString(4,obj.getRace());
           pstm.setInt(5,obj.getClientId());

           pstm.setInt(6,obj.getId());

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
    public void delete(Pet obj, Connection connection) {
        String sql = "DELETE FROM pet WHERE id=?";

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
    public void findById(Pet obj) {

    }

    @Override
    public List<Pet> findAll() {
        return null;
    }
}
