package dao.implement;

import dao.PetDAO;
import database.exceptions.DeleteErrorException;
import database.exceptions.GetErrorException;
import database.exceptions.InsertErrorException;
import database.exceptions.UpdateErrorException;
import entities.Pet;

import java.sql.*;
import java.util.List;


public class PetDaoJDBC implements PetDAO {
    @Override
    public int insert(Pet obj, Connection connection) {
        String sql = "INSERT INTO pet(name,age,species,race,clientId) VALUES (?,?,?,?,?)";
        int idGerado = -1;


        PreparedStatement pstm = null;


        try {
            pstm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1, obj.getName());
            pstm.setInt(2, obj.getAge());
            pstm.setString(3, obj.getSpecies());
            pstm.setString(4, obj.getRace());
            pstm.setInt(5, obj.getClientId());

            pstm.executeUpdate();

            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                    obj.setId(idGerado);
                    System.out.println(STR."ID do pet: \{idGerado}");
                } else {
                    throw new SQLException("Não foi possível obter o ID gerado automáticamente.");
                }
            }
        } catch (SQLException e) {
            throw new InsertErrorException(e.getMessage());
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idGerado;
    }

    @Override
    public void update(Pet obj, Connection connection) {
        String sql = "UPDATE pet SET clientId = COALESCE(?, clientId) WHERE id = ?";

        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(sql);

            if (obj.getClientId() != 0) {
                pstm.setInt(1, obj.getClientId());
            } else {
                pstm.setNull(1, Types.INTEGER);
            }
            pstm.setInt(2, obj.getId());

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
        String sql = "DELETE FROM pet WHERE id=?";

        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,id);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteErrorException(e.getMessage());
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
    public String get(int id, Connection connection) {
        String sql = "SELECT p.id, p.name, p.age, p.species, p.race, p.clientId, c.name AS ownerName " +
                "FROM pet p, client c WHERE p.id = ? AND p.clientId = c.id";

        Pet pet = new Pet();
        String petOwner = null;

        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {
                pet.setId(rset.getInt("id"));
                pet.setName(rset.getString("name"));
                pet.setAge(rset.getInt("age"));
                pet.setSpecies(rset.getString("species"));
                pet.setRace(rset.getString("race"));
                pet.setClientId(rset.getInt("clientId"));

                petOwner = rset.getString("ownerName");
            }
        } catch (SQLException e) {
            throw new GetErrorException(e.getMessage());
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(pet.toString()).append("\n");
        result.append("Nome do Dono: ").append(petOwner != null ? petOwner : "Desconhecido");

        return result.toString();
    }
}