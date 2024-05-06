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
    public void delete(Scheduling obj, Connection connection) {
        String sql = "DELETE FROM scheduling WHERE id=?";

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
    public String get(Scheduling obj, Connection connection) {
        String sql = "SELECT * FROM scheduling WHERE id =?";
        String clientName = "SELECT name FROM client WHERE id =?";
        String petName = "SELECT name FROM pet WHERE id =?";
        String serviceName = "SELECT name FROM service WHERE id =?";

        Scheduling scheduling = null;
        String client = null;
        String pet = null;
        String service = null;

        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, obj.getId());
            rset = pstm.executeQuery();

            while (rset.next()) {
                scheduling = new Scheduling();
                scheduling.setId(rset.getInt("id"));
                scheduling.setClientId(rset.getInt("clientId"));
                scheduling.setPetId(rset.getInt("petId"));
                scheduling.setServiceId(rset.getInt("serviceId"));
                java.sql.Date dataSQL = rset.getDate("date");
                LocalDateTime dataHora = dataSQL.toLocalDate().atStartOfDay();
                scheduling.setDate(dataHora);
            }
        } catch (SQLException e) {
            throw new GetErrorException(e.getMessage());
        }

        try {

            pstm = connection.prepareStatement(clientName);
            pstm.setInt(1, scheduling.getClientId());
            rset = pstm.executeQuery();
            if (rset.next()) {
                client = rset.getString("name");
            }
        } catch (SQLException e) {
            throw new GetErrorException(e.getMessage());
        }

        try {

            pstm = connection.prepareStatement(petName);
            pstm.setInt(1, scheduling.getPetId());
            rset = pstm.executeQuery();
            if (rset.next()) {
                pet = rset.getString("name");
            }
        } catch (SQLException e) {
            throw new GetErrorException(e.getMessage());
        }

        try {

            pstm = connection.prepareStatement(serviceName);
            pstm.setInt(1, scheduling.getServiceId());
            rset = pstm.executeQuery();
            if (rset.next()) {
                service = rset.getString("name");
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

        // Building the result string
        StringBuilder resultBuilder = new StringBuilder();
        if (client != null) {
            resultBuilder.append("Client Name: ").append(client).append("\n");
        }
        if (pet != null) {
            resultBuilder.append("Pet Name: ").append(pet).append("\n");
        }
        if (service != null) {
            resultBuilder.append("Service Name: ").append(service).append("\n");
        }
        return resultBuilder.toString();
    }

    @Override
    public List<Scheduling> findAll() {
        return null;
    }
}
