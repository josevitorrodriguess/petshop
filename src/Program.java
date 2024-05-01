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

//        Client ze = new Client(2,"jose","Horacio trajano","83988822933");
//
//        DaoFactory.createClientDAO().update(ze, connection);




//        Payment payment = new Payment(1,true);
//        DaoFactory.createPaymentDAO().update(payment,connection);



//        Pet toto = new Pet(1,"fumaça",2,"cachorro","beagle",2);
//        DaoFactory.createPetDAO().update(toto,connection);
//
//        Service servico = new Service("teste","teste",320.00);
//        servico.setId(1);

//        DaoFactory.createServiceDAO().update(servico,connection);
////
//        LocalDateTime data = LocalDateTime.of(2025, Month.APRIL, 25, 10, 0);
//
//
//        Scheduling agend = new Scheduling(1,data,1,1,1);
//        DaoFactory.createSchedulingDAO().update(agend,connection);
//
//        Payment payment = new Payment(agend.getId());
//        DaoFactory.createPaymentDAO().insert(payment);

        connection.close();
    }
}
