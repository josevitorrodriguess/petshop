package dao;

import dao.implement.*;

public class DaoFactory {

    public static ClientDAO createClientDAO(){
        return new CientDaoJDBC();
    }

    public static PetDAO createPetDAO(){
        return new PetDaoJDBC();
    }

    public static ServiceDAO createServiceDAO(){
        return new ServiceDaoJDBC();
    }

    public static SchedulingDAO createSchedulingDAO(){
        return new SchedulingDaoJDBC();
    }

    public static PaymentDAO createPaymentDAO(){
        return new PaymentDaoJDBC();
    }
}
