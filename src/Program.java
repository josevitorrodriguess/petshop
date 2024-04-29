import dao.DaoFactory;
import entities.*;

import java.time.LocalDateTime;
import java.time.Month;


public class Program {
    public static void main(String[] args) {


        Client ze = new Client("leticia","arnaldo costa","83987952342");
        DaoFactory.createClientDAO().insert(ze);

        Pet toto = new Pet("luna",4,"cachorro/dog","pitbull",ze.getId());
        DaoFactory.createPetDAO().insert(toto);

        Service servico = new Service("cortar orelha","cortar parte da orelha",300.00);
        DaoFactory.createServiceDAO().insert(servico);

        LocalDateTime data = LocalDateTime.of(2024, Month.MAY, 25, 10, 0);


        Scheduling agend = new Scheduling(data,ze.getId(),toto.getId(), servico.getId());
        DaoFactory.createSchedulingDAO().insert(agend);

        Payment payment = new Payment(agend.getId());
        DaoFactory.createPaymentDAO().insert(payment);


    }
}
