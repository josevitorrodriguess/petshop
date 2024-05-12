package dao.implement;

import dao.SchedulingDAO;
import database.exceptions.GetErrorException;
import database.exceptions.InsertErrorException;
import database.exceptions.UpdateErrorException;
import entities.Scheduling;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class SchedulingDaoJDBC implements SchedulingDAO {
    @Override
    public int[] insert(Scheduling obj, Connection connection) {
        String sql = "INSERT INTO scheduling(date, clientId, petId, serviceId) VALUES (?, ?, ?, ?)";
        String insertPayment = "INSERT INTO payment(schedulingId, payment) VALUES (?, ?)";
        int idGerado = -1;
        int idPagamento = -1;

        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Timestamp timestamp = Timestamp.valueOf(obj.getDate());

            pstm.setTimestamp(1, timestamp);
            pstm.setInt(2, obj.getClientId());
            pstm.setInt(3, obj.getPetId());
            pstm.setInt(4, obj.getServiceId());

            pstm.executeUpdate();

            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                    obj.setId(idGerado);
                    System.out.println("ID do agendamento: " + idGerado);
                } else {
                    throw new SQLException("Não foi possível obter o ID gerado automaticamente para o agendamento.");
                }
            }

            pstm = connection.prepareStatement(insertPayment, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, idGerado);
            pstm.setBoolean(2, false);

            pstm.executeUpdate();

            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    idPagamento = rs.getInt(1);
                    System.out.println("ID do pagamento: " + idPagamento);
                } else {
                    throw new SQLException("Não foi possível obter o ID gerado automaticamente para o pagamento.");
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
        return new int[] {idGerado, idPagamento};
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
            throw new UpdateErrorException(e.getMessage());
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
    public void delete(int id, Connection connection) {
        String sql = "DELETE FROM scheduling WHERE id = ?;";

        PreparedStatement pstm = null;

        try {
            pstm = connection.prepareStatement(sql);

            pstm.setInt(1,id);

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
        String sql = "SELECT sc.date, s.name AS serviceName, c.name AS clientName, p.name AS petName " +
                "FROM scheduling sc " +
                "JOIN service s ON sc.serviceId = s.id " +
                "JOIN client c ON sc.clientId = c.id " +
                "JOIN pet p ON sc.petId = p.id " +
                "WHERE sc.id = ?";

        String client = null;
        String pet = null;
        String service = null;
        LocalDateTime date = null;

        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {
                java.sql.Date dataSQL = rset.getDate("date");
                date = dataSQL.toLocalDate().atStartOfDay();

                client = rset.getString("clientName");
                service = rset.getString("serviceName");
                pet = rset.getString("petName");
            }
        } catch (SQLException e) {
            throw new GetErrorException(e.getMessage());
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        StringBuilder resultBuilder = new StringBuilder();
        if (date != null) {
            resultBuilder.append("Data: ").append(date).append("\n");
        }
        if (service != null) {
            resultBuilder.append("Serviço: ").append(service).append("\n");
        }
        if (pet != null) {
            resultBuilder.append("Pet: ").append(pet).append("\n");
        }
        if (client != null) {
            resultBuilder.append("Cliente: ").append(client).append("\n");
        }
        return resultBuilder.toString();
    }


}
