import dao.ClientDAO;
import dao.DaoFactory;
import database.DB;
import entities.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;


public class Program {
    public static void main(String[] args) throws SQLException {
        Connection connection = DB.getConnection();

//        Client ze = new Client(5,"Igor","Horacio trajano","83988888888");
//        DaoFactory.createClientDAO().delete(ze, connection);
////
//
//        Pet toto = new Pet("fumaça",2,"cachorro","beagle",5);
//        DaoFactory.createPetDAO().insert(toto,connection);
//
//        Service servico = new Service(2,"teste","teste",320.00);
//        DaoFactory.createServiceDAO().delete(servico,connection);


//        LocalDateTime data = LocalDateTime.of(2025, Month.APRIL, 25, 10, 0);
//
//
//        Scheduling agend = new Scheduling(2,data,5,4,2);
//        DaoFactory.createSchedulingDAO().delete(agend,connection);



//        Payment payment = new Payment(2,true);
//        DaoFactory.createPaymentDAO().delete(payment,connection);




        connection.close();
    }
}
