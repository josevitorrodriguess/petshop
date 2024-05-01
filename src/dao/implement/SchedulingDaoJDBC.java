package dao.implement;

import dao.SchedulingDAO;
import database.DB;
import database.exceptions.InsertErrorExeption;
import database.exceptions.UpdateErrorExeption;
import entities.Scheduling;

import java.sql.*;
import java.util.List;

public class SchedulingDaoJDBC implements SchedulingDAO {
    @Override
    public int insert(Scheduling obj, Connection connection) {
        String sql = ("INSERT INTO scheduling(date,clientId,petId,serviceId) VALUES (?,?,?,?)");
        int idGerado = -1;


        PreparedStatement pstm = null;

        try{
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            Timestamp timestamp = Timestamp.valueOf(obj.getDate());

            pstm.setTimestamp(1,timestamp);
            pstm.setInt(2,obj.getClientId());
            pstm.setInt(3,obj.getPetId());
            pstm.setInt(4,obj.getServiceId());

            pstm.executeUpdate();

            try(ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()){
                    idGerado = rs.getInt(1);
                    obj.setId(idGerado);
                    System.out.println(STR."ID do cliente: \{idGerado}");
                } else {
                    throw new SQLException("Não foi possível obter o ID gerado automaticamente.");
                }

            }
        } catch (SQLException e){
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
    public void update(Scheduling obj, Connection connection) {
        String sql = "UPDATE scheduling SET date = ? WHERE id =?";

        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(sql);

            Timestamp timestamp = Timestamp.valueOf(obj.getDate());

            pstm.setTimestamp(1,timestamp);
            pstm.setInt(2,obj.getId());

            pstm.executeUpdate();
        }catch (SQLException e){
            throw new UpdateErrorExeption(e.getMessage());
        } finally {
            try{
                if (pstm!=null){
                    pstm.close();
                }
            }  catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(Scheduling obj) {

    }

    @Override
    public void findById(Scheduling obj) {

    }

    @Override
    public List<Scheduling> findAll() {
        return null;
    }
}
