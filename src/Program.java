import dao.DaoFactory;
import database.DB;
import entities.*;


import java.time.LocalDateTime;

public class Program {
    public static void main(String[] args) {


        Client ze = new Client("ze","rua da mata","83988822933");


        DaoFactory.createClientDAO().insert(ze);

    }
}
