import dao.ClientDAO;
import dao.DaoFactory;
import database.DB;
import entities.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) throws SQLException {
        Connection connection = DB.getConnection();

//        Client leti = new Client("Letícia","Rua arnaldo costa 851","83987952342");
//
//        DaoFactory.createClientDAO().insert(leti,connection);





//        Pet juju = new Pet("jujuba",1,"cão","vira-lata",7);
//        Pet luna = new Pet("luna",2,"cão","pitbull",7);
//        DaoFactory.createPetDAO().insert(juju,connection);
//        DaoFactory.createPetDAO().insert(luna,connection);


//        Service servico = new Service("teste","teste",320.00);
//        DaoFactory.createServiceDAO().insert(servico,connection);
//
//        System.out.println(DaoFactory.createServiceDAO().get(servico,connection));


//        LocalDateTime data = LocalDateTime.of(2025, Month.APRIL, 25, 10, 0);
//
//
//        Scheduling agend = new Scheduling(data,8,6,4);
//        DaoFactory.createSchedulingDAO().insert(agend,connection);
//
//        System.out.println(DaoFactory.createSchedulingDAO().get(agend,connection));



        Payment payment = new Payment(3,10);
        System.out.println(DaoFactory.createPaymentDAO().get(payment,connection));




        connection.close();
    }
}
