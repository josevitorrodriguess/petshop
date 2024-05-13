package dao.implement;

import dao.ClientDAO;
import database.exceptions.DeleteErrorException;
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
        String sql = "UPDATE client SET name = COALESCE(?, name), address = COALESCE(?, address), number = COALESCE(?, number) WHERE id = ?";

        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(sql);


            if (obj.getName() != "") {
                pstm.setString(1, obj.getName());
            } else {
                pstm.setString(1, null);
            }

            if (obj.getAddress() !="") {
                pstm.setString(2, obj.getAddress());
            } else {
                pstm.setString(2, null);
            }

            if (obj.getPhoneNumber() != "") {
                pstm.setString(3, obj.getPhoneNumber());
            } else {
                pstm.setString(3, null);
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
        String sql = "DELETE FROM client WHERE id=?";

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
        String sql = "SELECT c.id, c.name, c.address, c.number, p.name AS clientPets FROM client c, pet p " +
                "WHERE c.id = ? AND p.clientId = c.id";


        Client client = new Client();
        List<String> clientPets = new ArrayList<>();
        String petName = null;

        PreparedStatement pstm = null;
        ResultSet rset = null;


        try {
            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,id);

            rset = pstm.executeQuery();

            while (rset.next()){
                client.setId(rset.getInt("id"));
                client.setName(rset.getString("name"));
                client.setAddress(rset.getString("address"));
                client.setPhoneNumber(rset.getString("number"));

                petName = rset.getString("clientPets");
                if (petName != null) {
                    clientPets.add(petName);
                }
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
}
