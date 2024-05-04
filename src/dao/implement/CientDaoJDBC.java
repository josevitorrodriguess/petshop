package dao.implement;

import dao.ClientDAO;
import database.exceptions.GetErrorException;
import database.exceptions.InsertErrorException;
import database.exceptions.UpdateErrorException;
import entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            throw new InsertErrorException(e.getMessage());
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
            throw new UpdateErrorException(e.getMessage());
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
    public String get(int id, Connection connection) {
        String sql = "SELECT * FROM client WHERE id=?";
        String sqlPet = "SELECT * FROM pet WHERE clientId = ?";

        Client client = new Client();
        List<String> clientPets = new ArrayList<>();

        PreparedStatement pstm = null;
        ResultSet rset = null;


        try {
            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,id);

            rset = pstm.executeQuery();

            while (rset.next()){
                client.setId(id);
                client.setName(rset.getString("name"));
                client.setAddress(rset.getString("address"));
                client.setPhoneNumber(rset.getString("number"));
            }

            pstm = connection.prepareStatement(sqlPet);

            pstm.setInt(1,id);

            rset = pstm.executeQuery();

            while (rset.next()){
                clientPets.add(rset.getString("name"));
            }
        } catch (SQLException e){
            throw new GetErrorException(e.getMessage());
        } finally {
            try {
                if (pstm!=null){
                    pstm.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(client.toString()).append("\n");
        result.append("Pets:\n");
        for (String pet : clientPets) {
            result.append(" - ").append(pet).append("\n");
        }
        return result.toString();
    }

    @Override
    public List<Client> findAll() {
        return null;
    }
}
